package aej.finalproject.ngojekkuy.error

import aej.finalproject.ngojekkuy.user.model.BaseResponse
import io.jsonwebtoken.UnsupportedJwtException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ErrorExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [ErrorException::class])
    fun userNotFoundException(throwable: Throwable): ResponseEntity<BaseResponse<Nothing>> {
        return ResponseEntity(BaseResponse.failure(throwable.message!!), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [UnsupportedJwtException::class])
    fun unsupportedJwtException(unsupportedJwtException: UnsupportedJwtException): ResponseEntity<BaseResponse<Nothing>> {
        return ResponseEntity(BaseResponse.failure(unsupportedJwtException.message!!), HttpStatus.FORBIDDEN)
    }
}