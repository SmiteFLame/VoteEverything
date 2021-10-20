package toy.vote.main.controller

import org.springframework.beans.factory.annotation.Autowired
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
import toy.vote.main.datasource.util.UserInput
import toy.vote.main.enumclass.Response
import toy.vote.main.exception.UserException
import toy.vote.main.exception.VoteException
import toy.vote.main.datasource.vote.entitiy.Vote
import toy.vote.main.datasource.vote.repository.VoteRepository
import toy.vote.main.datasource.vote.util.VoteInput
import toy.vote.main.service.UserService
import toy.vote.main.service.VoteService

@RestController
@RequestMapping("/vote")
class VoteController {
    @Autowired
    lateinit var voteService: VoteService

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var voteRepository: VoteRepository

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
     * 투표 단일 조회
     */
    @GetMapping("/name/{name}")
    fun selectVoteByName(@PathVariable name: String): ResponseEntity<Vote> {
        return ResponseEntity<Vote>(voteService.selectVoteByVoteName(name), HttpStatus.OK)
    }

    /**
     * 투표 전체 조회
     */
    @GetMapping("{word}")
    fun selectVotes(
        @PathVariable word: String,
        @RequestParam("id-type", defaultValue = "vote_id") idType: String
    ): ResponseEntity<List<Vote>> {
        if (idType == "name") {
            return ResponseEntity<List<Vote>>(voteRepository.findVotesByVoteName(word), HttpStatus.OK)
        }
        return ResponseEntity<List<Vote>>(voteRepository.findVotesByVoteId(word), HttpStatus.OK)
    }

    /**
     * 사용자 개인 투표
     */
    @PostMapping("/{vote_id}")
    fun insertVoteColumn(@PathVariable vote_id: String, @RequestParam userInput: UserInput): ResponseEntity<Response> {
        if (userRepository.findUserByEmail(userInput.email) == null) {
            throw UserException.NullUserException()
        }
        return ResponseEntity<Response>(voteService.insertVoteColumn(vote_id, userInput), HttpStatus.OK)
    }
}
