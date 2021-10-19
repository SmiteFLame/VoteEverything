package toy.vote.main.datasource.vote.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import toy.vote.main.datasource.vote.entitiy.VoteColumn

@Repository
interface VoteColumnRepository : JpaRepository<VoteColumn, String>
