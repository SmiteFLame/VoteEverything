package toy.vote.main.exception

import org.springframework.http.HttpStatus

abstract class VoteException(message : String) : Exception(message) {
    open var status : HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR

    class NullVoteException : VoteException("투표가 존재하지 않습니다") {
        override var status: HttpStatus = HttpStatus.NOT_FOUND
    }
}