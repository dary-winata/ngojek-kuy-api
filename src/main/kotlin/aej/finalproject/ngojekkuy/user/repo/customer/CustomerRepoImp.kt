package aej.finalproject.ngojekkuy.user.repo.customer

import aej.finalproject.ngojekkuy.database.DatabaseComponent
import aej.finalproject.ngojekkuy.error.ErrorException
import aej.finalproject.ngojekkuy.user.model.customer.CustomerDatabase
import aej.finalproject.ngojekkuy.user.model.customer.CustomerRequest
import com.fasterxml.jackson.datatype.jdk8.OptionalLongDeserializer
import com.mongodb.client.MongoCollection
import org.litote.kmongo.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class CustomerRepoImp: CustomerRepo {
    @Autowired
    private lateinit var databaseComponent: DatabaseComponent

    private fun customerCollection (): MongoCollection<CustomerDatabase> =
        databaseComponent.database.getDatabase("ngojek-kuy").getCollection()

    override fun getCustomerByUsername(username: String): CustomerDatabase {
        val customer = customerCollection().findOne(CustomerDatabase::username eq username)
        return if (customer != null) {
            customer
        } else {
            throw ErrorException("Customer not found")
        }
    }

    override fun insertCustomer(customer: CustomerRequest): CustomerDatabase {
        val customerDatabase = CustomerDatabase(
            id = UUID.randomUUID().toString(),
            username = customer.username,
            password = customer.password,
            name = customer.name,
        )
        val insert = customerCollection().insertOne(customerDatabase)

        return if (insert.wasAcknowledged()) {
            customerDatabase
        } else {
            throw ErrorException("Failed to insert customer")
        }
    }

    override fun updateCustomer(customer: CustomerRequest, id: String): CustomerDatabase {
        val oldCustomer = getCustomerById(id)

        val updatedCustomer = CustomerDatabase(
            id = oldCustomer.id,
            username = customer.username?:oldCustomer.username,
            password = customer.password?:oldCustomer.password,
            name = customer.name?:oldCustomer.name
        )

        val update = customerCollection().updateOne(CustomerDatabase::id eq id, updatedCustomer)
        return if(update.wasAcknowledged()) {
            updatedCustomer
        } else {
            throw ErrorException("Failed to update customer")
        }
    }

    override fun getCustomerById(id: String): CustomerDatabase {
        val user = customerCollection().findOne { CustomerDatabase::id eq id }
        if (user == null)
            throw ErrorException("Failed to update data")
        return user
    }
}