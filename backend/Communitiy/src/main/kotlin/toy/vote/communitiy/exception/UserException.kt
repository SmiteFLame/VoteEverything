package toy.vote.communitiy.exception

import org.springframework.http.HttpStatus

abstract class UserException(message: String) : Exception(message) {
    open var status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR

    class NullUserException : UserException("사용자가 존재하지 않습니다") {
        override var status: HttpStatus = HttpStatus.NOT_FOUND
    }

    class NotLoginException : UserException("로그인 되지 않은 유저입니다.") {
        override var status: HttpStatus = HttpStatus.BAD_REQUEST
    }

    class InvalidUserException : UserException("입력값이 존재하지 않습니다") {
        override var status: HttpStatus = HttpStatus.BAD_REQUEST
    }

    class PasswordException : UserException("비밀번호가 일치하지 않습니다") {
        override var status: HttpStatus = HttpStatus.BAD_REQUEST
    }
}
