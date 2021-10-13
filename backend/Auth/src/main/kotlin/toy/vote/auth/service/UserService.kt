package toy.vote.auth.service

import toy.vote.auth.datasource.user.entity.User

interface UserService {
    fun insertUser(user: User): String
    fun loginUser(email: String, password: String): String
}