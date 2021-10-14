package toy.vote.main.service

import toy.vote.main.datasource.user.entity.User

interface UserService {
    fun selectUserByEmail(email : String): User?
}