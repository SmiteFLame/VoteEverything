package toy.vote.auth.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import toy.vote.auth.exception.UserException
import toy.vote.auth.datasource.user.entity.User
import toy.vote.auth.datasource.user.repository.UserRepository
import toy.vote.auth.datasource.user.util.UserInput
import toy.vote.auth.datasource.user.util.UserOutput
import toy.vote.auth.service.UserService
import toy.vote.auth.util.JwtTokenProvider
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@CrossOrigin(origins = ["*"], maxAge = 6000)
@RequestMapping("/auth")
class AuthController {
    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @PostMapping("/user")
    fun insertUser(@RequestBody user: User?): ResponseEntity<User> {
        if (user == null) {
            throw UserException.InvalidUserException()
        }
        return ResponseEntity<User>(userService.insertUser(user), HttpStatus.CREATED)
    }

    @PostMapping("/login")
    fun login(@RequestBody userInput: UserInput?): ResponseEntity<UserOutput> {
        if (userInput == null) {
            throw UserException.InvalidUserException()
        }

        val user = userService.loginUser(userInput)
        val token = JwtTokenProvider.getToken(user.ndi)

        return ResponseEntity<UserOutput>(UserOutput(user, token), HttpStatus.OK)
    }

    @PostMapping("/logout")
    fun logout(response: HttpServletResponse): ResponseEntity<String> {
        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0
        response.addCookie(cookie)
        return ResponseEntity<String>("SUCCESS", HttpStatus.OK)
    }

    @GetMapping("user")
    fun findUser(@RequestHeader jwt: String?): ResponseEntity<User> {
        try {
            if (jwt == null) {
                throw UserException.WrongTokenException()
            }
            println(JwtTokenProvider.checkToken(jwt))
            return ResponseEntity<User>(userRepository.findUserByNdi(JwtTokenProvider.checkToken(jwt)), HttpStatus.OK)
        } catch (e: Exception) {
            throw UserException.WrongTokenException()
        }
    }
}
