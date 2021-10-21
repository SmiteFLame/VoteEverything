package toy.vote.main.datasource.vote.repository

import org.springframework.data.jpa.repository.JpaRepository
import toy.vote.main.datasource.vote.entitiy.VoteUser

interface VoteUserRepository : JpaRepository<VoteUser, String> {
    fun findVoteUserByColumnIdAndEmail(columnId: Int, email: String): VoteUser?

    fun findVoteUsersByColumnId(columnId: Int): List<VoteUser>
}
