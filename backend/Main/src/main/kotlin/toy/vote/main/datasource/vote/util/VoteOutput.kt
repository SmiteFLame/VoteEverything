package toy.vote.main.datasource.vote.util

import toy.vote.main.datasource.vote.entitiy.Vote

data class VoteOutput(
    val vote: Vote,
    val voteColumnOutputList: List<VoteColumnOutput>
)
