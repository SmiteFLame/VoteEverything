package toy.vote.communitiy.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import toy.vote.communitiy.datasource.community.entity.Comment
import toy.vote.communitiy.datasource.community.repository.CommentRepository
import toy.vote.communitiy.service.CommentService
import toy.vote.communitiy.service.UserService
import toy.vote.communitiy.util.OffsetBasedPageRequest

@RestController
@CrossOrigin(origins = ["*"], maxAge = 6000)
@RequestMapping("/comment")

class CommentController {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var commentService: CommentService

    @Autowired
    lateinit var commentRepository: CommentRepository

    @GetMapping()
    fun selectCommentsBy(
        @RequestParam(defaultValue = "10") limit: Int,
        @RequestParam(defaultValue = "0") offset: Long,
        @RequestParam map: Map<String, String>
    ): ResponseEntity<Page<Comment>>{
        val offsetBasedPageRequest = OffsetBasedPageRequest(limit, offset, map["sort-by"], map["order-by"], "commentId")

        val commentList = if(map.containsKey("communityId")){
            commentRepository.findCommentsByCommentId(map["communityId"]!!.toInt(), offsetBasedPageRequest)
        } else{
            commentRepository.findAll(offsetBasedPageRequest)
        }

        return ResponseEntity<Page<Comment>>(commentList, HttpStatus.OK)
    }


}