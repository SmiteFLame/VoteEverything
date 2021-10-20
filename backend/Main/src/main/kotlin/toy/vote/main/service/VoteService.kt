package toy.vote.main.service

import toy.vote.main.datasource.util.UserInput
import toy.vote.main.enumclass.Response
import toy.vote.main.datasource.vote.entitiy.Vote
import toy.vote.main.datasource.vote.util.VoteInput

interface VoteService {
    fun selectVoteByVoteName(name: String): Vote
    fun insertVote(voteInput: VoteInput): Response
    fun insertVoteColumn(vote_id: String, userInput: UserInput): Response
}
