package toy.vote.main.service

import toy.vote.main.wrapper.User

interface UserService {
    fun selectUserByEmail(email: String): User?
}
