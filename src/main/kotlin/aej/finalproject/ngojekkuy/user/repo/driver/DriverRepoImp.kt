package aej.finalproject.ngojekkuy.user.repo.driver

import aej.finalproject.ngojekkuy.database.DatabaseComponent
import aej.finalproject.ngojekkuy.error.ErrorException
import aej.finalproject.ngojekkuy.user.model.driver.DriverDatabase
import aej.finalproject.ngojekkuy.user.model.driver.DriverRequest
import org.litote.kmongo.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class DriverRepoImp: DriverRepo {
    @Autowired
    private lateinit var databaseComponent: DatabaseComponent

    private fun getDriverCollection() =
        databaseComponent.database.getDatabase("ngojek-kuy").getCollection<DriverDatabase>()

    override fun getDriverByUsername(username: String): DriverDatabase? {
        return getDriverCollection().findOne { DriverDatabase::username eq username }
    }

    override fun getDriverById(id: String): DriverDatabase? {
        return getDriverCollection().findOne { DriverDatabase::id eq id }
    }

    override fun addDriver(driverRequest: DriverRequest): DriverDatabase {
        if(getDriverByUsername(driverRequest.username) != null)
            throw ErrorException("Username sudah terpakai")

        val driverDatabase = DriverDatabase(
            id = UUID.randomUUID().toString(),
            username = driverRequest.username,
            password = driverRequest.password,
            name = driverRequest.name
        )

        val insert = getDriverCollection().insertOne(driverDatabase)

        return if(insert.wasAcknowledged()){
            driverDatabase
        } else {
            throw IllegalStateException("Gagal insert")
        }
    }

    override fun updateDriver(driverRequest: DriverRequest, id: String): DriverDatabase {
        if(getDriverById(id) == null)
            throw ErrorException("User tidak ada")

        val oldData = getDriverById(id)
        val driverDatabase = DriverDatabase(
            id = oldData!!.id,
            username = driverRequest.username?:oldData!!.username,
            password = driverRequest.password?:oldData!!.password,
            name = driverRequest.name?:oldData!!.name
        )

        getDriverCollection().updateOne(DriverDatabase::id eq id, driverDatabase)

        return driverDatabase
    }
}