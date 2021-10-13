package toy.vote.auth.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import toy.vote.auth.datasource.user.entity.User
import toy.vote.auth.datasource.user.repository.UserRepository
import toy.vote.auth.exception.UserException
import java.util.*

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

    override fun loginUser(email: String, password: String): String {
        val user = userRepository.findUserByEmail(email) ?: throw UserException.NullUserException()
        if(!passwordEncoder.matches(password, user.password)){
            throw UserException.PasswordException()
        }
        return "TOKEN"
    }


}