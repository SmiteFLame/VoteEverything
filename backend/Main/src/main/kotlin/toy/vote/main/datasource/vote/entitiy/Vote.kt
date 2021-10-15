package toy.vote.main.datasource.vote.entitiy

import org.springframework.data.redis.core.RedisHash
import java.sql.Timestamp
import javax.persistence.Id

@RedisHash("vote")
data class Vote(
    @Id
    var id: String? = null,
    val email: String,
    val time: Long,
    val name: String,
) {
    var startTime: Timestamp? = null
}
