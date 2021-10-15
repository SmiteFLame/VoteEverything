package toy.vote.main.datasource.vote.entitiy

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "vote_user")
@Entity
data class VoteUser(
    @Id
    @Column(name = "vote_user_id")
    var voteUserID: Int? = null,

    @Column(name = "column_id")
    val columnId: Int,

    val email: String
)
