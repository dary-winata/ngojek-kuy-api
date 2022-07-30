package aej.finalproject.ngojekkuy.model.driver

import com.fasterxml.jackson.annotation.JsonIgnore

data class DriverDatabase(
    val id: String = "",
    val username: String ="",
    val password: String = "",
    val name: String = ""
)
