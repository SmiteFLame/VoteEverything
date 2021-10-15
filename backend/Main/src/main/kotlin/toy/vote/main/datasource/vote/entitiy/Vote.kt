package toy.vote.main.datasource.vote.entitiy

import toy.vote.main.enumclass.VoteStatus
import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "vote")
@Entity
data class Vote(
    @Id
    @Column(name = "vote_id")
    var voteId: Int? = null,

    val email: String,

    val time: Long,

    @Column(name = "vote_name")
    val voteName: String,

    val status: VoteStatus
) {
    var startTime: Timestamp? = null
}
