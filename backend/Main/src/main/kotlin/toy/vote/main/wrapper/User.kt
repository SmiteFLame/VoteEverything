package toy.vote.main.wrapper

import toy.vote.main.enumclass.UserStatus

data class User(
    var ndi: String?,
    val email: String,
    var password: String,
    val status: UserStatus,
    val name: String
)
