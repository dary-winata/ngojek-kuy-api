package aej.finalproject.ngojekkuy.location.service

import aej.finalproject.ngojekkuy.location.entity.Coordinate
import aej.finalproject.ngojekkuy.location.entity.Location
import aej.finalproject.ngojekkuy.location.entity.LocationRoute
import aej.finalproject.ngojekkuy.location.mapper.Mapper
import aej.finalproject.ngojekkuy.location.network.LocationFetcherComponent
import aej.finalproject.ngojekkuy.orThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LocationServiceImp(
    @Autowired
    private val fetcherData: LocationFetcherComponent
): LocationService {
    override fun searchLocation(name: String, coordinate: Coordinate): Result<List<Location>> {
        return fetcherData.searchLocation(name, coordinate).map {
            Mapper.mapLocationHereToLocation(it)
        }
    }

    override fun reserveLocation(coordinate: Coordinate): Result<Location> {
        return fetcherData.reserveLocation(coordinate).map {
            Mapper.mapLocationHereReserveToLocation(it).first().orThrow("Location Not Found!")
        }
    }

    override fun routesLocation(origin: Coordinate, destination: Coordinate, transport: String): Result<LocationRoute> {
        return fetcherData.routeLocation(origin, destination, transport). map {
            val coordinate = Mapper.mapRouteHereToLocationRoute(it)
            LocationRoute(coordinate).orThrow("Route Not Found!")
        }
    }
}