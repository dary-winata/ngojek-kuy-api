package aej.finalproject.ngojekkuy.user.service.driver

import aej.finalproject.ngojekkuy.auth.jwt.JWTDriverConfig
import aej.finalproject.ngojekkuy.error.ErrorException
import aej.finalproject.ngojekkuy.user.model.LoginResponse
import aej.finalproject.ngojekkuy.user.model.driver.DriverDatabase
import aej.finalproject.ngojekkuy.user.model.driver.DriverLogin
import aej.finalproject.ngojekkuy.user.model.driver.DriverRequest
import aej.finalproject.ngojekkuy.orThrow
import aej.finalproject.ngojekkuy.user.repo.driver.DriverRepo
import aej.finalproject.ngojekkuy.toResult
import aej.finalproject.ngojekkuy.user.model.user.UserDatabase
import aej.finalproject.ngojekkuy.user.model.user.UserLogin
import aej.finalproject.ngojekkuy.user.model.user.UserRequest
import aej.finalproject.ngojekkuy.user.model.user.UserUpdate
import aej.finalproject.ngojekkuy.user.repo.user.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DriverServiceImp: DriverService {
    @Autowired
    private lateinit var userRepo: UserRepo

    override fun getDriversByUsername(username: String): Result<UserDatabase> {
        val user = userRepo.getUserByUsername(username)

        return if(user.role == "DRIVER") {
            user.toResult()
        } else {
            throw ErrorException("user not found!!!")
        }
    }

    override fun register(userRequest: UserRequest): Result<UserDatabase> {
       return userRepo.addUser(userRequest, "DRIVER").toResult()
    }

    override fun updateDriver(userUpdate: UserUpdate, id: String): Result<UserDatabase> {
        return userRepo.editUser(userUpdate, id).toResult()
    }

    override fun login(userLogin: UserLogin): Result<LoginResponse> {
        val user = userRepo.getUserByUsername(userLogin.username)

        return if(userLogin.password == user.password && user.role == "DRIVER"){
            user.toResult().map {
                val token = JWTDriverConfig.generateToken(it.id, it.role)
                LoginResponse(token)
            }
        } else {
            throw ErrorException("user not found!!!")
        }
    }
}