package aej.finalproject.ngojekkuy.controller

import aej.finalproject.ngojekkuy.model.BaseResponse
import aej.finalproject.ngojekkuy.model.LoginResponse
import aej.finalproject.ngojekkuy.model.customer.CustomerDatabase
import aej.finalproject.ngojekkuy.model.customer.CustomerLogin
import aej.finalproject.ngojekkuy.model.customer.CustomerRequest
import aej.finalproject.ngojekkuy.service.customer.CustomerService
import aej.finalproject.ngojekkuy.toResponse
import aej.finalproject.ngojekkuy.toResult
import com.fasterxml.jackson.databind.ser.Serializers.Base
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customer")
class CustomerController {
    @Autowired
    private lateinit var customerService: CustomerService

//    @GetMapping
//    fun getUser(): BaseResponse<Result<CustomerDatabase>> {
//        return BaseResponse(
//            status = true,
//            message = "Success",
//            data = Result(
//                customerService.getUserByUsername()
//            )
//        )
//    }

    @PostMapping("/register")
    fun registerCustomer(@RequestBody customerRequest: CustomerRequest): BaseResponse<CustomerDatabase> {
        return customerService.register(customerRequest).toResponse()
    }

    @PostMapping("/login")
    fun loginCustomer(@RequestBody loginCustomer: CustomerLogin): BaseResponse<LoginResponse> {
        return customerService.login(loginCustomer).toResponse()
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: String, @RequestBody customerRequest: CustomerRequest): BaseResponse<CustomerDatabase> {
        return customerService.updateCustomer(customerRequest, id).toResponse()
    }
}