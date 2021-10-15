package toy.vote.main.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import toy.vote.main.enumclass.Response
import toy.vote.main.exception.VoteException
import toy.vote.main.datasource.vote.entitiy.Vote
import toy.vote.main.datasource.vote.repository.VoteRepository
import java.sql.Timestamp
import java.util.UUID

@Service
class VoteServiceImpl : VoteService {

    @Autowired
    lateinit var voteRepository: VoteRepository

    override fun selectVoteByName(name: String): Vote {
        return voteRepository.findVoteByName(name) ?: throw VoteException.NullVoteException()
    }

    override fun insertVote(vote: Vote): Response {
        vote.id = UUID.randomUUID().toString()
        vote.startTime = Timestamp(System.currentTimeMillis())
        voteRepository.save(vote)
        print(voteRepository.findVoteById(vote.id!!))
        return Response.SUCCESS
    }
}
