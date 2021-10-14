package toy.vote.main.redis.repository

import org.springframework.data.repository.CrudRepository
import toy.vote.main.redis.entitiy.VoteRedis

interface VoteRedisRepository : CrudRepository<VoteRedis, String>{
}