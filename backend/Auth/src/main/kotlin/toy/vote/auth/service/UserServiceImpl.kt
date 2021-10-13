package toy.vote.auth.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import toy.vote.auth.datasource.user.entity.User
import toy.vote.auth.datasource.user.repository.UserRepository
import java.util.*

@Service
class UserServiceImpl : UserService {

    @Autowired
    lateinit var userRepository: UserRepository
    
    fun getToken() : String{
        val now = Date()
        TODO()
    }

    override fun insertUser(user: User): String {
        user.ndi = UUID.randomUUID().toString()
        userRepository.save(user)
        return "SUCCESS"
    }
}