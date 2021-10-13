package toy.vote.auth.datasource.user.entity

import toy.vote.auth.enumclass.UserStatus
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "user")
@Entity
data class User(
    @Id
    var ndi: String?,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    val status: UserStatus,

    @Column(nullable = false)
    val name: String
)