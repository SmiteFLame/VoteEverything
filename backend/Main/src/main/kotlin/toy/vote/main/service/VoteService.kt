package toy.vote.main.service

import toy.vote.main.enumclass.Response
import toy.vote.main.datasource.vote.entitiy.Vote
import toy.vote.main.datasource.vote.entitiy.VoteUser
import toy.vote.main.datasource.vote.util.VoteInput

interface VoteService {
    fun selectVoteByVoteName(name: String): Vote
    fun insertVote(voteInput: VoteInput): Response
    fun insertVoteColumn(voteUser: VoteUser): Response
}
