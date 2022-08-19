package aej.finalproject.ngojekkuy.user.service.customer

import aej.finalproject.ngojekkuy.user.model.LoginResponse
import aej.finalproject.ngojekkuy.user.model.customer.CustomerDatabase
import aej.finalproject.ngojekkuy.user.model.customer.CustomerLogin
import aej.finalproject.ngojekkuy.user.model.customer.CustomerRequest
import aej.finalproject.ngojekkuy.user.model.user.UserDatabase
import aej.finalproject.ngojekkuy.user.model.user.UserLogin
import aej.finalproject.ngojekkuy.user.model.user.UserRequest
import aej.finalproject.ngojekkuy.user.model.user.UserUpdate

interface CustomerService {
    fun register(userRequest: UserRequest): Result<UserDatabase>
    fun login(userLogin: UserLogin): Result<LoginResponse>
    fun updateCustomer(userUpdate: UserUpdate, id: String): Result<UserDatabase>
    fun getUserByUsername(username: String): Result<UserDatabase>
}