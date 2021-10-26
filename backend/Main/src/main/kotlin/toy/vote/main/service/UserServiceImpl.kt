package toy.vote.main.service

import org.springframework.stereotype.Service
import toy.vote.main.wrapper.User

@Service
class UserServiceImpl :UserService {

    override fun selectUserByEmail(email: String): User? {
        TODO("Not yet implemented")
    }
}
