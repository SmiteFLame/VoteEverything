package toy.vote.auth.service

import toy.vote.auth.datasource.user.entity.User
import toy.vote.auth.datasource.user.util.UserInput

interface UserService {
    fun insertUser(user: User): User
    fun loginUser(userInput: UserInput): User
}
