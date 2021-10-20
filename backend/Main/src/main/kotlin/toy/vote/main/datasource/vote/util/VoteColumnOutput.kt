package toy.vote.main.datasource.vote.util

import toy.vote.main.datasource.vote.entitiy.VoteColumn
import toy.vote.main.datasource.vote.entitiy.VoteUser

data class VoteColumnOutput(
    val voteColumn: VoteColumn,
    val voteUserList: List<VoteUser>
)