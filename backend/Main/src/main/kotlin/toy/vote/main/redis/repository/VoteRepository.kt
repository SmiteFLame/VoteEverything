package toy.vote.main.redis.repository

import org.springframework.data.repository.CrudRepository
import toy.vote.main.redis.entitiy.Vote

interface VoteRepository : CrudRepository<Vote, String> {
    fun findVotesByName(name: String): List<Vote>
    fun findVoteByName(name: String): Vote?
}
