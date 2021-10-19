package toy.vote.main.datasource.vote.entitiy

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "vote_column")
@Entity
class VoteColumn {
    @Id
    @Column(name = "column_id")
    val columnId: String = UUID.randomUUID().toString()

    @Column(name = "vote_id")
    val voteId: String

    @Column(name = "column_name")
    val columnName: String

    constructor(columnName: String, voteId: String) {
        this.voteId = voteId
        this.columnName = columnName
    }
}
