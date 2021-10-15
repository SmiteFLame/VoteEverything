package toy.vote.main.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import toy.vote.main.enumclass.Response
import toy.vote.main.exception.UserException
import toy.vote.main.exception.VoteException
import toy.vote.main.datasource.vote.entitiy.Vote
import toy.vote.main.datasource.vote.repository.VoteRepository
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
    lateinit var voteRepository: VoteRepository

    @PostMapping()
    fun insertVote(@RequestBody vote: Vote?): ResponseEntity<Response> {
        if (vote == null) {
            throw VoteException.NullVoteException()
        }
        val user = userService.selectUserByEmail(vote.email) ?: throw UserException.NullUserException()

        return ResponseEntity<Response>(voteService.insertVote(vote), HttpStatus.OK)
    }

    @GetMapping("/name/{name}")
    fun selectVoteByName(@PathVariable name: String): ResponseEntity<Vote> {
        return ResponseEntity<Vote>(voteService.selectVoteByName(name), HttpStatus.OK)
    }

    @GetMapping("{name}")
    fun selectVotes(@PathVariable name: String): ResponseEntity<List<Vote>> {
        return ResponseEntity<List<Vote>>(voteRepository.findVotesByName(name), HttpStatus.OK)
    }
}
