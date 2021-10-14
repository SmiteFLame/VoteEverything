package toy.vote.main.service

import toy.vote.main.enumclass.Response
import toy.vote.main.redis.entitiy.Vote

interface VoteService {
    fun insertVote(vote: Vote) : Response
}