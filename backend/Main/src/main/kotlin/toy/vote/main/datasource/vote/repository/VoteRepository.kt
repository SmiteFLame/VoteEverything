package toy.vote.main.datasource.vote.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import toy.vote.main.datasource.vote.entitiy.Vote

@Repository
interface VoteRepository : JpaRepository<Vote, String> {
    fun findVoteByVoteName(name: String): Vote?
    fun findVoteByVoteId(voteId: String): Vote?

    fun findVotesByVoteId(voteId: String): List<Vote>
    fun findVotesByVoteName(name: String): List<Vote>
}
