package aej.finalproject.ngojekkuy.user.model.user

data class UserRequest(
    val username: String,
    val password: String,
    val name: String = "guest"
)
