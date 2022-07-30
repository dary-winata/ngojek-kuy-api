package aej.finalproject.ngojekkuy.location.network

import aej.finalproject.ngojekkuy.location.entity.Coordinate
import aej.finalproject.ngojekkuy.location.entity.LocationHereReserveResponse
import aej.finalproject.ngojekkuy.location.entity.LocationHereResponse
import aej.finalproject.ngojekkuy.location.entity.LocationHereRouteResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException
import okhttp3.OkHttpClient
import okhttp3.Request
import org.bson.json.JsonParseException
import org.springframework.stereotype.Component

@Component
class LocationFetcherComponent {
    private val client = OkHttpClient()

    private inline fun <reified T> getHttp(url: String): Result<T> { //reified=menggunakan generic class sebagai java class
        return try {
            val request = Request.Builder()
                .url(url)
                .build()

            val response = client.newCall(request).execute()
            val body = response.body

            val bodyString = body?.string()
            if(response.isSuccessful){
                val data = ObjectMapper().readValue(bodyString, T::class.java) //butuh adapter tidak seperti retrofit, parsing manual
                Result.success(data)
            } else {
                val throwable = IllegalArgumentException(response.message)
                Result.failure(throwable)
            }
        } catch (e: JsonParseException) {
            Result.failure(e)

        } catch (e: InvalidDefinitionException){
            Result.failure(e)
        } catch (e: Throwable){ //undefined error
            Result.failure(e)
        }
    }

    fun searchLocation(name: String, coordinate: Coordinate): Result<LocationHereResponse> {
        val coordinateValue = "${coordinate.latitude},${coordinate.longitude}"
        val url = SEARCH_LOCATION
            .replace(Key.COORDINATE, coordinateValue)
            .replace(Key.NAME, name)

        return getHttp(url)
    }

    fun reserveLocation(coordinate: Coordinate): Result<LocationHereReserveResponse> {
        val coordinateValue = "${coordinate.latitude},${coordinate.longitude}"
        val url = REVERSE_LOCATION
            .replace(Key.COORDINATE, coordinateValue)

        return getHttp(url)
    }

    fun routeLocation(coordinateOrigin: Coordinate, coordinateDestination:Coordinate, transport: String): Result<LocationHereRouteResponse> {
        val origin = "${coordinateOrigin.latitude},${coordinateOrigin.longitude}"
        val destination = "${coordinateDestination.latitude},${coordinateDestination.longitude}"

        val url = ROUTE_LOCATION
            .replace(Key.COORDINATE_ORIGIN, origin)
            .replace(Key.COORDINATE_DESTINATION, destination)
            .replace(Key.TRANSPORT, transport)

        return getHttp(url)
    }

    companion object {
        const val SEARCH_LOCATION = "https://discover.search.hereapi.com/v1/discover?at={{coordinate}}&limit=10&q={{name}}&apiKey=1Pr7MiFr2UvVJHma2YexTm-1STPVLwDvENGbOs8GbfU"
        const val REVERSE_LOCATION = "https://revgeocode.search.hereapi.com/v1/revgeocode?at={{coordinate}}&apiKey=1Pr7MiFr2UvVJHma2YexTm-1STPVLwDvENGbOs8GbfU"
        const val ROUTE_LOCATION = "https://router.hereapi.com/v8/routes?transportMode={{transport}}&origin={{coordinate_origin}}&destination={{coordinate_destination}}&return=polyline&apikey=1Pr7MiFr2UvVJHma2YexTm-1STPVLwDvENGbOs8GbfU"
    }

    object Key{
        const val COORDINATE = "{{coordinate}}"
        const val NAME = "{{name}}"
        const val TRANSPORT = "{{transport}}"
        const val COORDINATE_ORIGIN = "{{coordinate_origin}}"
        const val COORDINATE_DESTINATION = "{{coordinate_destination}}"
    }
}