package toy.vote.main.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import toy.vote.main.datasource.user.repository.UserRepository
import toy.vote.main.enumclass.Response
import toy.vote.main.exception.UserException
import toy.vote.main.exception.VoteException
import toy.vote.main.datasource.vote.entitiy.Vote
import toy.vote.main.datasource.vote.entitiy.VoteColumn
import toy.vote.main.datasource.vote.entitiy.VoteUser
import toy.vote.main.datasource.vote.repository.VoteColumnRepository
import toy.vote.main.datasource.vote.repository.VoteRepository
import toy.vote.main.datasource.vote.util.VoteColumnOutput
import toy.vote.main.datasource.vote.util.VoteInput
import toy.vote.main.datasource.vote.util.VoteOutput
import toy.vote.main.service.UserService
import toy.vote.main.service.VoteService

@RestController
@RequestMapping("/votes")
class VoteController {
    @Autowired
    lateinit var voteService: VoteService

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var voteRepository: VoteRepository

    @Autowired
    lateinit var voteColumnRepository: VoteColumnRepository

    /**
     * 투표 전체 조회
     */
    @GetMapping
    fun selectVotes(
        @RequestParam(defaultValue = "10") limit: Int,
        @RequestParam(defaultValue = "0") offset: Long
    ): ResponseEntity<List<VoteOutput>> {
        val voteOutputs = voteService.selectVotes(limit, offset)

        if (voteOutputs.isEmpty()) {
            throw VoteException.NullVoteException()
        }

        return ResponseEntity<List<VoteOutput>>(voteOutputs, HttpStatus.OK)
    }

    /**
     * 투표 Best N 조회
     */
    @GetMapping("hottest-votes/{number}")
    fun selectBest(@PathVariable number: Int): ResponseEntity<List<VoteOutput>> {
        val voteOutputs = voteService.selectBestVotes(number)

        if (voteOutputs.isEmpty()) {
            throw VoteException.NullVoteException()
        }

        return ResponseEntity<List<VoteOutput>>(voteOutputs, HttpStatus.OK)
    }


    /**
     * 투표 단어 조회
     */
    @GetMapping("{word}")
    fun selectVotesByWord(
        @PathVariable word: String,
        @RequestParam("id-type", defaultValue = "vote_id") idType: String,
    ): ResponseEntity<List<Vote>> {
        if (idType == "name") {
            return ResponseEntity<List<Vote>>(voteRepository.findVotesByVoteName(word), HttpStatus.OK)
        }
        return ResponseEntity<List<Vote>>(voteRepository.findVotesByVoteId(word.toInt()), HttpStatus.OK)
    }


    /**
     * 투표 단일 조회
     */
    @GetMapping("/name/{name}")
    fun selectVoteByName(@PathVariable name: String): ResponseEntity<VoteOutput> {
        return ResponseEntity<VoteOutput>(voteService.selectVoteByVoteName(name), HttpStatus.OK)
    }

    /**
     * 투표 항목 조회
     */
    @GetMapping("/columns/{vote_id}")
    fun selectVoteColumns(@PathVariable vote_id: Int): ResponseEntity<List<VoteColumnOutput>> {
        if (voteRepository.findVoteByVoteId(vote_id) == null) {
            throw VoteException.NullVoteException()
        }
        return ResponseEntity<List<VoteColumnOutput>>(voteService.selectVoteColumnsByVoteId(vote_id), HttpStatus.OK)
    }


    /**
     * 새로운 투표 추가
     */
    @PostMapping()
    fun insertVote(@RequestBody voteInput: VoteInput?): ResponseEntity<Response> {
        if (voteInput == null) {
            throw VoteException.NullVoteException()
        }

        userService.selectUserByEmail(voteInput.email) ?: throw UserException.NullUserException()

        return ResponseEntity<Response>(voteService.insertVote(voteInput), HttpStatus.OK)
    }


    /**
     * 사용자 개인 투표
     */
    @PostMapping("/user")
    fun insertVoteColumn(@RequestBody voteUser: VoteUser): ResponseEntity<Response> {
        if (userRepository.findUserByEmail(voteUser.email) == null) {
            throw UserException.NullUserException()
        }
        return ResponseEntity<Response>(voteService.insertVoteColumn(voteUser), HttpStatus.OK)
    }
}
