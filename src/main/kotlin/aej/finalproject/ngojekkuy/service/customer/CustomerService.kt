package aej.finalproject.ngojekkuy.service.customer

import aej.finalproject.ngojekkuy.model.LoginResponse
import aej.finalproject.ngojekkuy.model.customer.CustomerDatabase
import aej.finalproject.ngojekkuy.model.customer.CustomerLogin
import aej.finalproject.ngojekkuy.model.customer.CustomerRequest

interface CustomerService {
    fun register(customerRequest: CustomerRequest): Result<CustomerDatabase>
    fun login(customerLogin: CustomerLogin): Result<LoginResponse>
    fun updateCustomer(customer: CustomerRequest, id: String): Result<CustomerDatabase>
    fun getUserByUsername(username: String): Result<CustomerDatabase>
}