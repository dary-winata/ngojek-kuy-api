package aej.finalproject.ngojekkuy.controller

import aej.finalproject.ngojekkuy.model.BaseResponse
import aej.finalproject.ngojekkuy.model.LoginResponse
import aej.finalproject.ngojekkuy.model.driver.DriverDatabase
import aej.finalproject.ngojekkuy.model.driver.DriverLogin
import aej.finalproject.ngojekkuy.model.driver.DriverRequest
import aej.finalproject.ngojekkuy.service.driver.DriverService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import aej.finalproject.ngojekkuy.toResponse

@RestController
@RequestMapping("/driver")
class DriverController {
    @Autowired
    private lateinit var driverService: DriverService

    @GetMapping
    fun getDrivers(): BaseResponse<List<DriverDatabase>>{
        return driverService.getDrivers().toResponse()
    }

    @PostMapping("/register")
    fun registerDriver(@RequestBody driverRequest: DriverRequest): BaseResponse<DriverDatabase> {
        return driverService.register(driverRequest).toResponse()
    }

    @PutMapping("/{id}")
    fun updateDriver(@RequestBody driverRequest: DriverRequest, @PathVariable id: String): BaseResponse<DriverDatabase> {
        return driverService.updateDriver(driverRequest, id).toResponse()
    }

    @PostMapping("/login")
    fun loginDriver(@RequestBody driverLogin: DriverLogin): BaseResponse<LoginResponse> {
        return driverService.login(driverLogin).toResponse()
    }
}