package toy.vote.main.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import toy.vote.main.datasource.user.repository.UserRepository
import toy.vote.main.enumclass.Response
import toy.vote.main.redis.entitiy.VoteRedis
import toy.vote.main.redis.repository.VoteRedisRepository

@Service
class VoteServiceImpl : VoteService {

    @Autowired
    lateinit var voteRepository: VoteRedisRepository


    override fun insertVote(vote: VoteRedis): Response {
        print(vote.toString())
        voteRepository.save(vote)
        return Response.SUCCESS
    }
}