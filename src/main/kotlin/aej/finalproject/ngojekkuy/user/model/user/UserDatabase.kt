package aej.finalproject.ngojekkuy.user.model.user

import aej.finalproject.ngojekkuy.location.entity.Location

data class UserDatabase(
    val id: String = "",
    val username: String ="",
    val password: String = "",
    val name: String = "",
    val role: String = "",
    val location:Location? = Location()
)
