package toy.vote.communitiy.datasource.community.entity

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "comment")
@Entity
class Comment {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val commentId: Int = -1

    @Column(name = "community_id", nullable = false)
    val communityId: Int

    @Column(nullable = false)
    val email: String

    @Column(nullable = false)
    val name: String

    @Column(nullable = false)
    val time: Timestamp

    @Column(nullable = false)
    val content: String

    constructor(communityId: Int, email: String, name: String, content: String) {
        this.communityId = communityId
        this.email = email
        this.name = name
        this.time = Timestamp(System.currentTimeMillis())
        this.content = content
    }
}
