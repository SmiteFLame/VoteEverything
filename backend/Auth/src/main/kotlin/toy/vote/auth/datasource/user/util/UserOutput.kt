package toy.vote.auth.datasource.user.util

import toy.vote.auth.datasource.user.entity.User

data class UserOutput(
    val user: User,
    val jwt: String
)
