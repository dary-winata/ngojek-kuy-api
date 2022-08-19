package aej.finalproject.ngojekkuy.user.model.customer

import aej.finalproject.ngojekkuy.location.entity.Location

data class CustomerDatabase(
    val id: String,
    val username: String,
    val password: String,
    val name: String = "",
    val location: Location? = Location()
)
