package toy.vote.main.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import toy.vote.main.datasource.user.repository.UserRepository
import toy.vote.main.enumclass.Response
import toy.vote.main.exception.UserException
import toy.vote.main.exception.VoteException
import toy.vote.main.redis.entitiy.VoteRedis
import toy.vote.main.service.UserService
import toy.vote.main.service.VoteService

@RestController
@RequestMapping("/vote")
class VoteController {
    @Autowired
    lateinit var voteService : VoteService

    @Autowired
    lateinit var userService : UserService

    @PostMapping()
    fun insertVote(@RequestBody vote: VoteRedis?) : ResponseEntity<Response>{
        if(vote == null){
            throw VoteException.NullVoteException()
        }
        val user = userService.selectUserByEmail(vote.email) ?: throw UserException.NullUserException()

        return ResponseEntity<Response>(voteService.insertVote(vote), HttpStatus.OK)
    }
}