package toy.vote.main.exception

import org.springframework.http.HttpStatus

abstract class VoteException(message: String) : Exception(message) {
    open var status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR

    class NullVoteException : VoteException("투표가 존재하지 않습니다") {
        override var status: HttpStatus = HttpStatus.NOT_FOUND
    }

    class NullVoteColumnException : VoteException("투표 항목이 존재하지 않습니다") {
        override var status: HttpStatus = HttpStatus.NOT_FOUND
    }

    class DuplicationVoteUserException : VoteException("이미 투표를 완료했습니다.") {
        override var status: HttpStatus = HttpStatus.BAD_REQUEST
    }
}
