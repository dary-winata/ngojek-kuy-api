package aej.finalproject.ngojekkuy.user.controller

import aej.finalproject.ngojekkuy.user.model.BaseResponse
import aej.finalproject.ngojekkuy.user.model.LoginResponse
import aej.finalproject.ngojekkuy.user.model.customer.CustomerDatabase
import aej.finalproject.ngojekkuy.user.model.customer.CustomerLogin
import aej.finalproject.ngojekkuy.user.model.customer.CustomerRequest
import aej.finalproject.ngojekkuy.user.service.customer.CustomerService
import aej.finalproject.ngojekkuy.toResponse
import aej.finalproject.ngojekkuy.toResult
import aej.finalproject.ngojekkuy.user.model.user.UserDatabase
import aej.finalproject.ngojekkuy.user.model.user.UserLogin
import aej.finalproject.ngojekkuy.user.model.user.UserRequest
import aej.finalproject.ngojekkuy.user.model.user.UserUpdate
import com.fasterxml.jackson.databind.ser.Serializers.Base
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.security.RolesAllowed

@RestController
@RequestMapping("/customer")
class CustomerController {
    @Autowired
    private lateinit var customerService: CustomerService

    @GetMapping("/{username}")
    fun getUser(@PathVariable username: String): BaseResponse<UserDatabase> {
        return customerService.getUserByUsername(username).toResponse()
    }

    @PostMapping("/register")
    fun registerCustomer(@RequestBody userRequest: UserRequest): BaseResponse<UserDatabase> {
        return customerService.register(userRequest).toResponse()
    }

    @PostMapping("/login")
    fun loginCustomer(@RequestBody userLogin: UserLogin): BaseResponse<LoginResponse> {
        return customerService.login(userLogin).toResponse()
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: String, @RequestBody userUpdate: UserUpdate): BaseResponse<UserDatabase> {
        return customerService.updateCustomer(userUpdate, id).toResponse()
    }
}