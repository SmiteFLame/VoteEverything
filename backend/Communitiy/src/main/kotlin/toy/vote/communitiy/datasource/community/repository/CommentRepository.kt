package toy.vote.communitiy.datasource.community.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import toy.vote.communitiy.datasource.community.entity.Comment

interface CommentRepository: JpaRepository<Comment, String>{
    fun findCommentsByCommentId(communityId: Int, pageable: Pageable): Page<Comment>
}
