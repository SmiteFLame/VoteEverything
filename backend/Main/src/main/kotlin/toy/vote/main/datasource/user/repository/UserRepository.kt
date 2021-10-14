package toy.vote.main.datasource.user.repository

import toy.vote.main.datasource.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository :JpaRepository<User, String>{
    fun findUserByEmail(email : String) : User?
}