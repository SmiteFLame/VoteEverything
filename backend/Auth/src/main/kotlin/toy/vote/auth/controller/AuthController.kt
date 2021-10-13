package toy.vote.auth.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import toy.vote.auth.exception.UserException
import toy.vote.auth.datasource.user.entity.User
import toy.vote.auth.datasource.user.repository.UserRepository
import toy.vote.auth.service.UserService

@RestController
@RequestMapping("/auth")
class AuthController {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var userService: UserService

    @PostMapping("/token")
    fun getToken() : ResponseEntity<String>{
        TODO()
    }

    @PostMapping("/user")
    fun insertUser(@RequestBody user : User?) : ResponseEntity<String>{
        if(user == null){
            throw UserException.InvalidUserException()
        }
        return ResponseEntity<String>(userService.insertUser(user), HttpStatus.CREATED)
    }
}