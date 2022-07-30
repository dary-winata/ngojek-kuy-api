package aej.finalproject.ngojekkuy.location.controller

import aej.finalproject.ngojekkuy.location.service.LocationService
import aej.finalproject.ngojekkuy.location.entity.Location
import aej.finalproject.ngojekkuy.location.entity.LocationRoute
import aej.finalproject.ngojekkuy.model.BaseResponse
import aej.finalproject.ngojekkuy.toCoordinate
import aej.finalproject.ngojekkuy.toResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/loc")
class LocationController {
    @Autowired
    private lateinit var locationService: LocationService

    @GetMapping("/search")
    fun searchLocation(
        @RequestParam name: String,
        @RequestParam coordinate: String
    ): BaseResponse<List<Location>>{
        val coordinateValue = coordinate.toCoordinate()

        return locationService.searchLocation(name, coordinateValue).toResponse()
    }

    @GetMapping("/reverse")
    fun reverseLocation(
        @RequestParam coordinate: String
    ): BaseResponse<Location> {
        val coordinateValue = coordinate.toCoordinate()

        return locationService.reserveLocation(coordinateValue).toResponse()
    }

    @GetMapping("/route")
    fun routeLocation(
        @RequestParam coordinateOrigin: String,
        @RequestParam coordinateDestination: String,
        @RequestParam transportMode: String
    ): BaseResponse<LocationRoute> {
        val origin = coordinateOrigin.toCoordinate()
        val destination = coordinateDestination.toCoordinate()

        return locationService.routesLocation(origin, destination, transportMode).toResponse()
    }
}