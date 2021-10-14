package toy.vote.main.service

import toy.vote.main.enumclass.Response
import toy.vote.main.redis.entitiy.VoteRedis

interface VoteService {
    fun insertVote(vote: VoteRedis) : Response
}