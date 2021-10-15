package toy.vote.main.datasource.vote.entitiy

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "vote_column")
@Entity
data class VoteColumn(
    @Id
    @Column(name = "column_id")
    var columnId: Int? = null,

    @Column(name = "vote_id")
    val voteId: Int,

    @Column(name = "column_name")
    val columnName: String
)
