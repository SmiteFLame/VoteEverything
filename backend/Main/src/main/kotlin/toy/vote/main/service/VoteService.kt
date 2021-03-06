package toy.vote.main.service

import toy.vote.main.enumclass.Response
import toy.vote.main.datasource.vote.entitiy.VoteUser
import toy.vote.main.datasource.vote.util.VoteColumnOutput
import toy.vote.main.datasource.vote.util.VoteInput
import toy.vote.main.datasource.vote.util.VoteOutput

interface VoteService {
    fun selectVotes(limit: Int, offset: Long): List<VoteOutput>
    fun selectBestVotes(number: Int): List<VoteOutput>
    fun selectVoteByVoteName(name: String): VoteOutput
    fun selectVoteColumnsByVoteId(vote_id: Int): List<VoteColumnOutput>
    fun insertVote(voteInput: VoteInput): Response
    fun insertVoteColumn(voteUser: VoteUser): Response
}
