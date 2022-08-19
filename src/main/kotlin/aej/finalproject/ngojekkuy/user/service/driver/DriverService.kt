package aej.finalproject.ngojekkuy.user.service.driver

import aej.finalproject.ngojekkuy.user.model.LoginResponse
import aej.finalproject.ngojekkuy.user.model.driver.DriverDatabase
import aej.finalproject.ngojekkuy.user.model.driver.DriverLogin
import aej.finalproject.ngojekkuy.user.model.driver.DriverRequest
import aej.finalproject.ngojekkuy.user.model.user.UserDatabase
import aej.finalproject.ngojekkuy.user.model.user.UserLogin
import aej.finalproject.ngojekkuy.user.model.user.UserRequest
import aej.finalproject.ngojekkuy.user.model.user.UserUpdate

interface DriverService {
    fun getDriversByUsername(username: String): Result<UserDatabase>
    fun register(userRequest: UserRequest): Result<UserDatabase>
    fun updateDriver(userUpdate: UserUpdate, id: String): Result<UserDatabase>
    fun login(userLogin: UserLogin): Result<LoginResponse>
}