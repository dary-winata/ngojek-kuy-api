package aej.finalproject.ngojekkuy.user.repo.driver

import aej.finalproject.ngojekkuy.user.model.driver.DriverDatabase
import aej.finalproject.ngojekkuy.user.model.driver.DriverRequest

interface DriverRepo {
    fun getDriverByUsername(username: String): DriverDatabase?
    fun getDriverById(id: String): DriverDatabase?
    fun addDriver(driverRequest: DriverRequest): DriverDatabase
    fun updateDriver(driverRequest: DriverRequest, id: String): DriverDatabase
}