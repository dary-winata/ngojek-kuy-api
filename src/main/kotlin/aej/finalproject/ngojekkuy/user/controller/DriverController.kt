package aej.finalproject.ngojekkuy.user.controller

import aej.finalproject.ngojekkuy.user.model.BaseResponse
import aej.finalproject.ngojekkuy.user.model.LoginResponse
import aej.finalproject.ngojekkuy.user.model.driver.DriverDatabase
import aej.finalproject.ngojekkuy.user.model.driver.DriverLogin
import aej.finalproject.ngojekkuy.user.model.driver.DriverRequest
import aej.finalproject.ngojekkuy.user.service.driver.DriverService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import aej.finalproject.ngojekkuy.toResponse
import aej.finalproject.ngojekkuy.user.model.user.UserDatabase
import aej.finalproject.ngojekkuy.user.model.user.UserLogin
import aej.finalproject.ngojekkuy.user.model.user.UserRequest
import aej.finalproject.ngojekkuy.user.model.user.UserUpdate
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RequestParam
import javax.annotation.security.RolesAllowed

@RestController
@RequestMapping("/driver")
class DriverController {
    @Autowired
    private lateinit var driverService: DriverService

    @GetMapping("/{username}")
    fun getDrivers(@PathVariable username: String): BaseResponse<UserDatabase> {
        return driverService.getDriversByUsername(username).toResponse()
    }

    @PostMapping("/register")
    fun registerDriver(@RequestBody userRequest: UserRequest): BaseResponse<UserDatabase> {
        return driverService.register(userRequest).toResponse()
    }

    @PutMapping("/{id}")
    fun updateDriver(@RequestBody userUpdate: UserUpdate, @PathVariable id: String): BaseResponse<UserDatabase> {
        return driverService.updateDriver(userUpdate, id).toResponse()
    }

    @PostMapping("/login")
    fun loginDriver(@RequestBody userLogin: UserLogin): BaseResponse<LoginResponse> {
        return driverService.login(userLogin).toResponse()
    }
}