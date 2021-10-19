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
import toy.vote.main.enumclass.Response
import toy.vote.main.exception.UserException
import toy.vote.main.exception.VoteException
import toy.vote.main.datasource.vote.entitiy.Vote
import toy.vote.main.datasource.vote.entitiy.VoteColumn
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
    lateinit var voteRepository: VoteRepository

    @PostMapping()
    fun insertVote(@RequestBody voteInput: VoteInput?): ResponseEntity<Response> {
        if (voteInput == null) {
            throw VoteException.NullVoteException()
        }


        userService.selectUserByEmail(voteInput.email) ?: throw UserException.NullUserException()

        return ResponseEntity<Response>(voteService.insertVote(voteInput), HttpStatus.OK)
    }

    @GetMapping("/name/{name}")
    fun selectVoteByName(@PathVariable name: String): ResponseEntity<Vote> {
        return ResponseEntity<Vote>(voteService.selectVoteByVoteName(name), HttpStatus.OK)
    }

    @GetMapping("{word}")
    fun selectVotes(@PathVariable word: String, @RequestParam("id-type", defaultValue = "vote_id") idType: String): ResponseEntity<List<Vote>> {
        if(idType == "name"){
            return ResponseEntity<List<Vote>>(voteRepository.findVotesByVoteName(word), HttpStatus.OK)
        }
        return ResponseEntity<List<Vote>>(voteRepository.findVotesByVoteId(word), HttpStatus.OK)
    }
}
