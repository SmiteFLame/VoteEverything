package toy.vote.main.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import toy.vote.main.enumclass.Response
import toy.vote.main.exception.VoteException
import toy.vote.main.redis.entitiy.Vote
import toy.vote.main.redis.repository.VoteRepository

@Service
class VoteServiceImpl : VoteService {

    @Autowired
    lateinit var voteRepository: VoteRepository

    override fun selectVoteByName(name: String): Vote {
        return voteRepository.findVoteByName(name) ?: throw VoteException.NullVoteException()
    }

    override fun insertVote(vote: Vote): Response {
        voteRepository.save(vote)
        return Response.SUCCESS
    }
}