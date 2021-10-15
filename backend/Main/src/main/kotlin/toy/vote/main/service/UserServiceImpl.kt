package toy.vote.main.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import toy.vote.main.datasource.user.entity.User
import toy.vote.main.datasource.user.repository.UserRepository

@Service
class UserServiceImpl : UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    override fun selectUserByEmail(email: String): User? {
        return userRepository.findUserByEmail(email)
    }
}
