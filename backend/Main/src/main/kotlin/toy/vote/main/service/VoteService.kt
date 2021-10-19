package toy.vote.main.service

import toy.vote.main.enumclass.Response
import toy.vote.main.datasource.vote.entitiy.Vote
import toy.vote.main.datasource.vote.util.VoteInput

interface VoteService {
    fun selectVoteByName(name: String): Vote
    fun insertVote(voteInput: VoteInput): Response
}
