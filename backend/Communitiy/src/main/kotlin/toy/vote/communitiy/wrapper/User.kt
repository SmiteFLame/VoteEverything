package toy.vote.communitiy.wrapper

import toy.vote.communitiy.enumclass.UserStatus

data class User(
    var ndi: String?,
    val email: String,
    var password: String,
    val status: UserStatus,
    val name: String
)
