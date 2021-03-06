package toy.vote.main.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import toy.vote.main.enumclass.Response
import toy.vote.main.exception.VoteException
import toy.vote.main.datasource.vote.entitiy.Vote
import toy.vote.main.datasource.vote.entitiy.VoteColumn
import toy.vote.main.datasource.vote.entitiy.VoteUser
import toy.vote.main.datasource.vote.repository.VoteColumnRepository
import toy.vote.main.datasource.vote.repository.VoteRepository
import toy.vote.main.datasource.vote.repository.VoteUserRepository
import toy.vote.main.datasource.vote.util.VoteColumnOutput
import toy.vote.main.datasource.vote.util.VoteInput
import toy.vote.main.datasource.vote.util.VoteOutput
import toy.vote.main.util.OffsetBasedPageRequest
import java.util.*

@Service
class VoteServiceImpl : VoteService {

    @Autowired
    lateinit var voteRepository: VoteRepository

    @Autowired
    lateinit var voteColumnRepository: VoteColumnRepository

    @Autowired
    lateinit var voteUserRepository: VoteUserRepository

    override fun selectVotes(limit: Int, offset: Long): List<VoteOutput> {
        val votes = voteRepository.findAll(OffsetBasedPageRequest(limit, offset, Sort.by("voteId")))

        val voteOutputs = ArrayList<VoteOutput>()
        votes.forEach { vote ->
            voteOutputs.add(VoteOutput(vote, selectVoteColumnsByVoteId(vote.voteId)))
        }

        return voteOutputs
    }

    override fun selectBestVotes(number: Int): List<VoteOutput> {
        val votes = voteRepository.findAll()
        val pQueue = PriorityQueue(Comparator<VoteOutput> { o1, o2 ->
            var o1Sum = 0
            var o2Sum = 0
            o1.voteColumnOutputList.forEach { next ->
                o1Sum += next.voteUserList.size
            }
            o2.voteColumnOutputList.forEach { next ->
                o2Sum += next.voteUserList.size
            }
            o2Sum - o1Sum
        })
        votes.forEach { vote ->
            pQueue.offer(VoteOutput(vote, selectVoteColumnsByVoteId(vote.voteId)))
        }
        val voteOutputs = ArrayList<VoteOutput>()

        while (voteOutputs.size < number && !pQueue.isEmpty()) {
            voteOutputs.add(pQueue.poll())
        }

        return voteOutputs
    }

    override fun selectVoteByVoteName(name: String): VoteOutput {
        val vote = voteRepository.findVoteByVoteName(name) ?: throw VoteException.NullVoteException()
        return VoteOutput(vote, selectVoteColumnsByVoteId(vote.voteId))
    }

    override fun selectVoteColumnsByVoteId(vote_id: Int): List<VoteColumnOutput> {
        val voteColumnOutPutList = LinkedList<VoteColumnOutput>()
        voteColumnRepository.findVoteColumnsByVoteId(vote_id).forEach { voteColumn ->
            val voteUserList = voteUserRepository.findVoteUsersByColumnId(voteColumn.columnId)
            voteColumnOutPutList.add(VoteColumnOutput(voteColumn, voteUserList))
        }
        return voteColumnOutPutList
    }

    @Transactional
    override fun insertVote(voteInput: VoteInput): Response {
        val vote = voteRepository.save(Vote(voteInput))
        voteInput.voteColumn.forEach { voteColumn ->
            voteColumnRepository.save(VoteColumn(voteColumn.columnName, vote.voteId))
        }
        return Response.SUCCESS
    }

    @Transactional
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
