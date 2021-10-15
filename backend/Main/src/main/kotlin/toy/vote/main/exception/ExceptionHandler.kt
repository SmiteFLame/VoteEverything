package toy.vote.main.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler

class ExceptionHandler {
    @ExceptionHandler
    fun userExceptionHandler(error: UserException): ResponseEntity<String> {
        return ResponseEntity<String>(error.message, error.status)
    }

    @ExceptionHandler
    fun voteExceptionHandler(error: VoteException): ResponseEntity<String> {
        return ResponseEntity<String>(error.message, error.status)
    }

    @ExceptionHandler
    fun exceptionHandler(error: Exception): ResponseEntity<String> {
        var status = HttpStatus.INTERNAL_SERVER_ERROR
        return ResponseEntity<String>(error.message, status)
    }
}
