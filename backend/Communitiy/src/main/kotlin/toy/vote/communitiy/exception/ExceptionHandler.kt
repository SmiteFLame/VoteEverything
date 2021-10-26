package toy.vote.communitiy.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler
    fun exceptionHandler(error: Exception): ResponseEntity<String> {
        var status = HttpStatus.INTERNAL_SERVER_ERROR
        return ResponseEntity<String>(error.message, status)
    }
}