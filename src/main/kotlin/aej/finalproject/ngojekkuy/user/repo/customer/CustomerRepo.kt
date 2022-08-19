package aej.finalproject.ngojekkuy.user.repo.customer

import aej.finalproject.ngojekkuy.user.model.customer.CustomerDatabase
import aej.finalproject.ngojekkuy.user.model.customer.CustomerRequest

interface CustomerRepo {
    fun getCustomerByUsername(username: String): CustomerDatabase
    fun insertCustomer(customer: CustomerRequest): CustomerDatabase
    fun updateCustomer(customer: CustomerRequest, id: String): CustomerDatabase
    fun getCustomerById(id: String): CustomerDatabase
}