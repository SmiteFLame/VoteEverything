package toy.vote.communitiy.service

import toy.vote.communitiy.wrapper.User

interface UserService {
    fun selectUserByJwt(jwt: String): User?
}
