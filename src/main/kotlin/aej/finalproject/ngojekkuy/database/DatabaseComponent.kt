package aej.finalproject.ngojekkuy.database

import com.mongodb.client.MongoClient
import org.litote.kmongo.KMongo
import org.springframework.stereotype.Component

@Component
class DatabaseComponent {
    companion object {
        private const val MONGO_URL = "mongodb+srv://darywinata:zabuza0920@oneforall.ar0ve.mongodb.net/?retryWrites=true&w=majority"
    }

    val database: MongoClient = KMongo.createClient(MONGO_URL)
}