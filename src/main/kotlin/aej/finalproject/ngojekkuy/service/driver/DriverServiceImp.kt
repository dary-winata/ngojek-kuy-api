package aej.finalproject.ngojekkuy.service.driver

import aej.finalproject.ngojekkuy.auth.jwt.JWTDriverConfig
import aej.finalproject.ngojekkuy.error.ErrorException
import aej.finalproject.ngojekkuy.model.LoginResponse
import aej.finalproject.ngojekkuy.model.driver.DriverDatabase
import aej.finalproject.ngojekkuy.model.driver.DriverLogin
import aej.finalproject.ngojekkuy.model.driver.DriverRequest
import aej.finalproject.ngojekkuy.repo.DriverRepo
import aej.finalproject.ngojekkuy.toResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DriverServiceImp: DriverService {
    @Autowired
    private lateinit var driverRepo: DriverRepo

    override fun getDrivers(): Result<List<DriverDatabase>> {
        return driverRepo.getDrivers().toResult()
    }

    override fun register(driverRequest: DriverRequest): Result<DriverDatabase> {
        return driverRepo.addDriver(driverRequest).toResult()
    }

    override fun updateDriver(driverRequest: DriverRequest, id: String): Result<DriverDatabase> {
        return driverRepo.updateDriver(driverRequest, id).toResult()
    }

    override fun login(driverLogin: DriverLogin): Result<LoginResponse> {
        val getUser = driverRepo.getDriverByName(driverLogin.username)

        if(getUser == null || getUser.password != driverLogin.password)
            return throw ErrorException("Username atau password tidak ada")

        return getUser.toResult().map {
            val token = JWTDriverConfig.generateToken(it)
            LoginResponse(token)
        }
    }
}