package toy.vote.main.datasource.vote.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import toy.vote.main.datasource.vote.entitiy.Vote

@Repository
interface VoteRepository : JpaRepository<Vote, String> {
    fun findVotesByName(name: String): List<Vote>
    fun findVoteByName(name: String): Vote?
    fun findVoteById(id: String): Vote?
}
