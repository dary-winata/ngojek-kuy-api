package aej.finalproject.ngojekkuy.user.repo.user

import aej.finalproject.ngojekkuy.database.DatabaseComponent
import aej.finalproject.ngojekkuy.error.ErrorException
import aej.finalproject.ngojekkuy.user.model.customer.CustomerDatabase
import aej.finalproject.ngojekkuy.user.model.user.UserDatabase
import aej.finalproject.ngojekkuy.user.model.user.UserRequest
import aej.finalproject.ngojekkuy.user.model.user.UserUpdate
import com.mongodb.client.MongoCollection
import org.litote.kmongo.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class UserRepoImp: UserRepo {

    @Autowired
    private lateinit var databaseComponent: DatabaseComponent

    private fun getUserCollection(): MongoCollection<UserDatabase> =
        databaseComponent.database.getDatabase("ngojek-kuy").getCollection()

    override fun getUserByUsername(username: String): UserDatabase {
        val user = getUserCollection().findOne(UserDatabase::username eq username)
        return if(user != null){
            user
        } else {
            throw ErrorException("User Not Found!!!")
        }
    }

    override fun addUser(userRequest: UserRequest, role: String): UserDatabase {
        val userDatabase = UserDatabase(
            id = UUID.randomUUID().toString(),
            username = userRequest.username,
            password = userRequest.password,
            name = userRequest.name,
            role = role
        )

        val insert = getUserCollection().insertOne(userDatabase)

        return if(insert.wasAcknowledged()) {
            userDatabase
        } else {
            throw ErrorException("error inserting data!!!")
        }
    }

    override fun editUser(userUpdate: UserUpdate, id: String): UserDatabase {
        getUserById(userUpdate.username?:"")

        val oldUser = getUserById(id)
        val user = UserDatabase(
            id = oldUser.id,
            username = userUpdate.username?:oldUser.username,
            password = userUpdate.password?:oldUser.password,
            name = userUpdate.name?:oldUser.name
        )

        val update = getUserCollection().updateOne(UserDatabase::id eq id, user)

        return if(update.wasAcknowledged()) {
            user
        } else {
            throw ErrorException("data tidak terupdate!!!")
        }
    }

    override fun getUserById(id: String): UserDatabase {
        val user = getUserCollection().findOne(UserDatabase::id eq id)

        return if(user != null) {
            user
        } else {
            throw ErrorException("User Not Found!!!")
        }
    }
}