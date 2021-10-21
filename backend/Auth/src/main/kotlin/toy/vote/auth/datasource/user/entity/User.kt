package toy.vote.auth.datasource.user.entity

import toy.vote.auth.enumclass.UserStatus
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "user")
@Entity
class User {

    @Id
    var ndi: String = UUID.randomUUID().toString()

    @Column(nullable = false, unique = true)
    val email: String

    @Column(nullable = false)
    var password: String

    @Column(nullable = false)
    val status: UserStatus

    @Column(nullable = false)
    val name: String

    constructor(email: String, password: String, status: UserStatus, name: String) {
        this.email = email
        this.password = password
        this.status = status
        this.name = name
    }
}
