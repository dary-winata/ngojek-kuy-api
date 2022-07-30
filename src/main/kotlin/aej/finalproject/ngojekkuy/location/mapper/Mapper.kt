package aej.finalproject.ngojekkuy.location.mapper

import aej.finalproject.ngojekkuy.PolylineEncoderDecoder
import aej.finalproject.ngojekkuy.location.entity.*

object Mapper {
    fun mapLocationHereToLocation(locationSearchResponse: LocationHereResponse): List<Location> {
        return locationSearchResponse.items?.map {
            val address = Location.Address(
                city = it?.address?.city.orEmpty(),
                country = it?.address?.countryName.orEmpty(),
                district = it?.address?.district.orEmpty()
            )

            Location(
                name = it?.title.orEmpty(),
                address = address,
                coordinate = Coordinate(it?.position?.lat ?: 0.0, it?.position?.lng ?: 0.0)
            )
        }.orEmpty()
    }

    fun mapLocationHereReserveToLocation(locationSearchResponse: LocationHereReserveResponse): List<Location> {
        return locationSearchResponse.items?.map {
            val address = Location.Address(
                city = it?.address?.city.orEmpty(),
                country = it?.address?.countryName.orEmpty(),
                district = it?.address?.district.orEmpty()
            )

            Location(
                name = it?.title.orEmpty(),
                address = address,
                coordinate = Coordinate(it?.position?.lat ?: 0.0, it?.position?.lng ?: 0.0)
            )
        }.orEmpty()
    }

    fun mapRouteHereToLocationRoute(locationHereRouteResponse: LocationHereRouteResponse): List<Coordinate> {
        val polylineString = locationHereRouteResponse.routes
            ?.first()
            ?.sections
            ?.first()
            ?.polyline
            .orEmpty()

        val coordinate = PolylineEncoderDecoder.decode(polylineString).map {
            Coordinate(it.lat, it.lng)
        }

        return coordinate
    }

//    fun reserveToLocation(locationHereResponse: LocationHereResponse): Location {
//        return locationHereResponse.items?.mapIndexed { index, item ->
//            if ()
//            val address = Location.Address(
//                city = it?.address?.city.orEmpty(),
//                country = it?.address?.countryName.orEmpty(),
//                district = it?.address?.district.orEmpty()
//            )
//
//            Location(
//                name = it?.title.orEmpty(),
//                address = address,
//                coordinate = Coordinate(it?.position?.lat ?: 0.0, it?.position?.lng ?: 0.0)
//            )
//        }
//    }
}