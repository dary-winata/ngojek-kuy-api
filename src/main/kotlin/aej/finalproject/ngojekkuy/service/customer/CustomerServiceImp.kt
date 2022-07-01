package aej.finalproject.ngojekkuy.service.customer

import aej.finalproject.ngojekkuy.auth.jwt.JWTDriverConfig
import aej.finalproject.ngojekkuy.error.ErrorException
import aej.finalproject.ngojekkuy.model.LoginResponse
import aej.finalproject.ngojekkuy.model.customer.CustomerDatabase
import aej.finalproject.ngojekkuy.model.customer.CustomerLogin
import aej.finalproject.ngojekkuy.model.customer.CustomerRequest
import aej.finalproject.ngojekkuy.repo.customer.CustomerRepo
import aej.finalproject.ngojekkuy.toResult
import org.apache.juli.logging.Log
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerServiceImp: CustomerService {
    @Autowired
    private lateinit var customerRepository: CustomerRepo

    override fun register(customerRequest: CustomerRequest): Result<CustomerDatabase> {
        return customerRepository.insertCustomer(customerRequest).toResult()
    }

    override fun login(customerLogin: CustomerLogin): Result<LoginResponse> {
        val user = customerRepository.getCustomerByUsername(customerLogin.username)
        if (user.password != customerLogin.password)
            throw ErrorException("Wrong password")

        return user.toResult().map {
            val token = JWTDriverConfig.generateToken(it.id, it.username)
            LoginResponse(token)
        }
    }

    override fun updateCustomer(customer: CustomerRequest, id: String): Result<CustomerDatabase> {
        return customerRepository.updateCustomer(customer, id).toResult()
    }
    override fun getUserByUsername(username: String): Result<CustomerDatabase> {
        return customerRepository.getCustomerByUsername(username).toResult()
    }
}