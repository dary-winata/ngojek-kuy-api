package aej.finalproject.ngojekkuy.location.service

import aej.finalproject.ngojekkuy.location.entity.Coordinate
import aej.finalproject.ngojekkuy.location.entity.Location
import aej.finalproject.ngojekkuy.location.entity.LocationRoute

interface LocationService {
     fun searchLocation(name: String, coordinate: Coordinate): Result<List<Location>>
     fun reserveLocation(coordinate: Coordinate): Result<Location>
     fun routesLocation(origin: Coordinate, destination: Coordinate, transport: String): Result<LocationRoute>
}