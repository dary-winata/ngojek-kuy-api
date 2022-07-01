package aej.finalproject.ngojekkuy.repo

import aej.finalproject.ngojekkuy.model.driver.DriverDatabase
import aej.finalproject.ngojekkuy.model.driver.DriverRequest

interface DriverRepo {
    fun getDrivers(): List<DriverDatabase>
    fun getDriverByName(username: String): DriverDatabase?
    fun getDriverById(id: String): DriverDatabase?
    fun addDriver(driverRequest: DriverRequest): DriverDatabase
    fun updateDriver(driverRequest: DriverRequest, id: String): DriverDatabase
}