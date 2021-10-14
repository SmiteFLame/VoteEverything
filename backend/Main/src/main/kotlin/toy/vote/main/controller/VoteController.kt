package toy.vote.main.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import toy.vote.main.enumclass.Response
import toy.vote.main.exception.VoteException
import toy.vote.main.redis.entitiy.Vote
import toy.vote.main.service.VoteService

@RestController
@RequestMapping("/vote")
class VoteController {
    @Autowired
    lateinit var voteService : VoteService

    @PostMapping()
    fun insertVote(@RequestBody vote: Vote?) : ResponseEntity<Response>{
        if(vote == null){
            throw VoteException.NullVoteException()
        }
        return ResponseEntity<Response>(voteService.insertVote(vote), HttpStatus.OK)
    }
}