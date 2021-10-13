package toy.vote.auth.datasource.user.repository

import toy.vote.auth.datasource.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository :JpaRepository<User, String>{
    fun findUserByEmail(email : String) : User?
}