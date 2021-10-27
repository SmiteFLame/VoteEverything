package toy.vote.communitiy.datasource.community.entity

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "community")
@Entity
class Community {
    @Id
    @Column(name = "community_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val communityId: Int = -1

    @Column(nullable = false)
    val email: String

    @Column(nullable = false)
    val name: String

    @Column(nullable = false)
    val title: String

    @Column(nullable = false)
    val time: Timestamp

    @Column(nullable = false)
    val content: String

    constructor(email: String, name: String, title: String, content: String) {
        this.email = email
        this.name = name
        this.title = title
        this.time = Timestamp(System.currentTimeMillis())
        this.content = content
    }
}
