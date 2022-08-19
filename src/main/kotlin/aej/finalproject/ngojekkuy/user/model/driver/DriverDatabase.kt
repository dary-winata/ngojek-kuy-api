package aej.finalproject.ngojekkuy.user.model.driver

import aej.finalproject.ngojekkuy.location.entity.Location
import com.fasterxml.jackson.annotation.JsonIgnore

data class DriverDatabase(
    val id: String = "",
    val username: String ="",
    val password: String = "",
    val name: String = "",
    val location:Location? = Location()
)
