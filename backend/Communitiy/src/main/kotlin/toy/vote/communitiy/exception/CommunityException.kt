package toy.vote.communitiy.exception

import org.springframework.http.HttpStatus

abstract class CommunityException(message: String) : Exception(message) {
    open var status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR

    class NullCommunityException : CommunityException("게시물이 존재하지 않습니다.") {
        override var status: HttpStatus = HttpStatus.NOT_FOUND
    }
}
