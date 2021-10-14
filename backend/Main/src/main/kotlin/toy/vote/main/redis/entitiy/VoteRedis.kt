package toy.vote.main.redis.entitiy

import org.springframework.data.redis.core.RedisHash
import java.sql.Time
import java.sql.Timestamp
import javax.persistence.Id

@RedisHash("vote")
data class VoteRedis(
    @Id
    val id: Int? = null,
    val email: String,
    val time: Long,
    val name: String,
){
    var startTime : Timestamp? = null
}