package aej.finalproject.ngojekkuy.service.driver

import aej.finalproject.ngojekkuy.model.LoginResponse
import aej.finalproject.ngojekkuy.model.driver.DriverDatabase
import aej.finalproject.ngojekkuy.model.driver.DriverLogin
import aej.finalproject.ngojekkuy.model.driver.DriverRequest

interface DriverService {
    fun getDriversByUsername(username: String): Result<DriverDatabase>
    fun register(driverRequest: DriverRequest): Result<DriverDatabase>
    fun updateDriver(driverRequest: DriverRequest, id: String): Result<DriverDatabase>
    fun login(driverLogin: DriverLogin): Result<LoginResponse>
}