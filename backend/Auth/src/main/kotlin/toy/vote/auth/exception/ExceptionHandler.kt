package toy.vote.auth.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler
    fun userExceptionHandler(error : UserException) : ResponseEntity<String>{
        return ResponseEntity<String>(error.message, error.status)
    }
}