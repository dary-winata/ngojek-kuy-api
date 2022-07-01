package aej.finalproject.ngojekkuy.model.driver

import com.fasterxml.jackson.annotation.JsonIgnore

data class DriverDatabase(
    val id: String = "",
    var username: String ="",
    var password: String = "",
    var name: String = ""
)
