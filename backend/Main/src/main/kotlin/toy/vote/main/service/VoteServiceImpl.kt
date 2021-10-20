package toy.vote.main.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import toy.vote.main.enumclass.Response
import toy.vote.main.exception.VoteException
import toy.vote.main.datasource.vote.entitiy.Vote
import toy.vote.main.datasource.vote.entitiy.VoteColumn
import toy.vote.main.datasource.vote.entitiy.VoteUser
import toy.vote.main.datasource.vote.repository.VoteColumnRepository
import toy.vote.main.datasource.vote.repository.VoteRepository
import toy.vote.main.datasource.vote.repository.VoteUserRepository
import toy.vote.main.datasource.vote.util.VoteInput

@Service
class VoteServiceImpl : VoteService {

    @Autowired
    lateinit var voteRepository: VoteRepository

    @Autowired
    lateinit var voteColumnRepository: VoteColumnRepository

    @Autowired
    lateinit var voteUserRepository: VoteUserRepository

    override fun selectVoteByVoteName(name: String): Vote {
        return voteRepository.findVoteByVoteName(name) ?: throw VoteException.NullVoteException()
    }

    override fun insertVote(voteInput: VoteInput): Response {
        val vote = voteRepository.save(Vote(voteInput))
        voteInput.voteColumn.forEach { voteColumn ->
            voteColumnRepository.save(VoteColumn(voteColumn.columnName, vote.voteId!!))
        }
        return Response.SUCCESS
    }

    override fun insertVoteColumn(voteUser: VoteUser): Response {
        if (voteColumnRepository.findVoteColumnByColumnId(voteUser.columnId) == null) {
            throw VoteException.NullVoteColumnException()
        }
        if (voteUserRepository.findVoteUserByColumnIdAndEmail(voteUser.columnId, voteUser.email) != null) {
            throw VoteException.DuplicationVoteUserException()
        }

        voteUserRepository.save(voteUser)

        return Response.SUCCESS
    }
}
