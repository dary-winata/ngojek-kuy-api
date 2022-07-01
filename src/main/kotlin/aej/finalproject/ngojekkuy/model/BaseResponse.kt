package aej.finalproject.ngojekkuy.model

open class BaseResponse <T> (
    var status: String = "200",
    var message: String = "success",
    var data: T? = null
    ) {
    companion object {
        fun <T>success(data: T?): BaseResponse<T>{
            return BaseResponse(data = data)
        }

        fun <T>failure(message: String): BaseResponse<T> {
            return BaseResponse(status = "400", message = message)
        }
    }
}