package toy.vote.main.redis.entitiy

import org.springframework.data.redis.core.RedisHash
import java.sql.Timestamp
import javax.persistence.Id

@RedisHash("vote", timeToLive = 30)
class Vote (
    @Id
    val id : Int,
    val email : String,
    val startTime : Timestamp,
    val endTime : Timestamp,
    val name: String,
        )