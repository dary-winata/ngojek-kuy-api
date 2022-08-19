package aej.finalproject.ngojekkuy.user.service.customer

import aej.finalproject.ngojekkuy.auth.jwt.JWTDriverConfig
import aej.finalproject.ngojekkuy.error.ErrorException
import aej.finalproject.ngojekkuy.user.model.LoginResponse
import aej.finalproject.ngojekkuy.user.model.customer.CustomerDatabase
import aej.finalproject.ngojekkuy.user.model.customer.CustomerLogin
import aej.finalproject.ngojekkuy.user.model.customer.CustomerRequest
import aej.finalproject.ngojekkuy.user.repo.customer.CustomerRepo
import aej.finalproject.ngojekkuy.toResult
import aej.finalproject.ngojekkuy.user.model.user.UserDatabase
import aej.finalproject.ngojekkuy.user.model.user.UserLogin
import aej.finalproject.ngojekkuy.user.model.user.UserRequest
import aej.finalproject.ngojekkuy.user.model.user.UserUpdate
import aej.finalproject.ngojekkuy.user.repo.user.UserRepo
import org.apache.juli.logging.Log
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerServiceImp: CustomerService {
    @Autowired
    private lateinit var userRepo: UserRepo

    override fun register(userRequest: UserRequest): Result<UserDatabase> {
        return userRepo.addUser(userRequest, "CUSTOMER").toResult()
    }

    override fun login(userLogin: UserLogin): Result<LoginResponse> {
        val user = userRepo.getUserByUsername(userLogin.username)

        return if(user.role == "CUSTOMER" && user.password == userLogin.password) {
            user.toResult().map {
                val token = JWTDriverConfig.generateToken(it.id, it.role)
                LoginResponse(token)
            }
        } else {
            throw ErrorException("user not found!!!")
        }
    }

    override fun updateCustomer(userUpdate: UserUpdate, id: String): Result<UserDatabase> {
        return userRepo.editUser(userUpdate, id).toResult()
    }

    override fun getUserByUsername(username: String): Result<UserDatabase> {
        val user = userRepo.getUserByUsername(username)

        return if(user.role == "CUSTOMER") {
            user.toResult()
        } else {
            throw ErrorException("user not found!!!")
        }
    }


}