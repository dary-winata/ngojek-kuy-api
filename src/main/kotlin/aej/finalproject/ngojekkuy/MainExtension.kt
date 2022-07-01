package aej.finalproject.ngojekkuy

import aej.finalproject.ngojekkuy.error.ErrorException
import aej.finalproject.ngojekkuy.model.BaseResponse

inline fun <reified T> T.toResult(message: String = "${T::class.simpleName} is null"): Result<T> {
    return if(this != null) {
        Result.success(this)
    } else {
        Result.failure(ErrorException(message))
    }
}

fun <T>Result<T>.toResponse(): BaseResponse<T> {
    return if(this.isFailure) {
        throw ErrorException(this.exceptionOrNull()?.message ?: "Failed")
    } else {
        BaseResponse.success(this.getOrNull())
    }
}