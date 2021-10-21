package toy.vote.main.datasource.vote.entitiy

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "vote_column")
@Entity
class VoteColumn {
    @Id
    @Column(name = "column_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val columnId: Int = -1

    @Column(name = "vote_id")
    val voteId: Int

    @Column(name = "column_name")
    val columnName: String

    constructor(columnName: String, voteId: Int) {
        this.voteId = voteId
        this.columnName = columnName
    }
}
