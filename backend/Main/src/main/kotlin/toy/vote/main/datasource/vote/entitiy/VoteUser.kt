package toy.vote.main.datasource.vote.entitiy

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "vote_user")
@Entity
class VoteUser {
    @Id
    @Column(name = "vote_user_id")
    var voteUserId: String = UUID.randomUUID().toString()

    @Column(name = "column_id")
    val columnId: String

    @Column
    val email: String

    constructor(columnId: String, email: String) {
        this.columnId = columnId
        this.email = email
    }
}
