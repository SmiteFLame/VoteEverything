package toy.vote.main.datasource.vote.entitiy

import toy.vote.main.datasource.vote.util.VoteInput
import toy.vote.main.enumclass.VoteStatus
import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "vote")
@Entity
class Vote {
    @Id
    @Column(name = "vote_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val voteId: Int = -1

    @Column(nullable = false)
    val email: String

    @Column(nullable = false)
    val time: Long

    @Column(name = "vote_name")
    val voteName: String

    @Column(nullable = false)
    val status: VoteStatus

    @Column(name = "start_time")
    var startTime: Timestamp? = null

    constructor(voteInput: VoteInput) {
        this.email = voteInput.email
        this.time = voteInput.time
        this.voteName = voteInput.voteName
        this.status = VoteStatus.NORMAL
        this.startTime = Timestamp(System.currentTimeMillis())
    }
}
