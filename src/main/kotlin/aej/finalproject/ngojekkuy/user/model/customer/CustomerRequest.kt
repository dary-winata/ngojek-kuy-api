package aej.finalproject.ngojekkuy.user.model.customer

data class CustomerRequest(
    val username: String,
    val password: String,
    val name: String = ""
)
