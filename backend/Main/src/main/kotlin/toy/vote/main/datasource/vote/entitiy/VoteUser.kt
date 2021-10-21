package toy.vote.main.datasource.vote.entitiy

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "vote_user")
@Entity
class VoteUser {
    @Id
    @Column(name = "vote_user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var voteUserId: Int = -1

    @Column(name = "column_id")
    val columnId: Int

    @Column
    val email: String

    constructor(columnId: Int, email: String) {
        this.columnId = columnId
        this.email = email
    }
}
