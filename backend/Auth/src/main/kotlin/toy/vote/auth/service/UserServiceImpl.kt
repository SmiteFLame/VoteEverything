package toy.vote.auth.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import toy.vote.auth.datasource.user.entity.User
import toy.vote.auth.datasource.user.repository.UserRepository
import toy.vote.auth.datasource.user.util.UserInput
import toy.vote.auth.exception.UserException
import toy.vote.auth.util.JwtTokenProvider
import java.util.UUID

@Service
class UserServiceImpl : UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    override fun insertUser(user: User): String {
        user.ndi = UUID.randomUUID().toString()
        user.password = passwordEncoder.encode(user.password)
        userRepository.save(user)
        return "SUCCESS"
    }

    override fun loginUser(userInput: UserInput): String {
        val user = userRepository.findUserByEmail(userInput.email) ?: throw UserException.NullUserException()
        if (!passwordEncoder.matches(userInput.password, user.password)) {
            throw UserException.WrongPasswordException()
        }
        return JwtTokenProvider.getToken(user.ndi)
    }
}
