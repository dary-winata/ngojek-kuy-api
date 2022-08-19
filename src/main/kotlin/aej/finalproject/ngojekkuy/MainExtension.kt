package aej.finalproject.ngojekkuy

import aej.finalproject.ngojekkuy.error.ErrorException
import aej.finalproject.ngojekkuy.location.entity.Coordinate
import aej.finalproject.ngojekkuy.user.model.BaseResponse

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

fun String.toCoordinate(): Coordinate {
    val splitString = split(",")
    val lat = splitString[0].toDouble() ?: 0.0
    val lng = splitString[1].toDouble() ?: 0.0

    return Coordinate(lat, lng)
}

inline fun <reified T> T?.orThrow(message : String = "${T::class.simpleName} null"): T {
    return this ?: throw ErrorException(message)
}