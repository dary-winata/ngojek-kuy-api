package aej.finalproject.ngojekkuy.database

import com.mongodb.client.MongoClient
import org.litote.kmongo.KMongo
import org.springframework.stereotype.Component

@Component
class DatabaseComponent {
    private val mongoUrl = System.getenv("DATABASE_URL").replace("\"", "")

    val database: MongoClient = KMongo.createClient(mongoUrl)

    init {
        println("cek database -> $mongoUrl")
    }
}