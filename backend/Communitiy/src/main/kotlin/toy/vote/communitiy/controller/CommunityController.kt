package toy.vote.communitiy.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import toy.vote.communitiy.datasource.community.entity.Community
import toy.vote.communitiy.exception.CommunityException
import toy.vote.communitiy.exception.UserException
import toy.vote.communitiy.service.CommunityService
import toy.vote.communitiy.service.UserService

@RestController
@RequestMapping("/community")
class CommunityController{

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var communityService: CommunityService

    @GetMapping
    fun selectCommunities(
        @RequestParam(defaultValue = "10") limit: Int,
        @RequestParam(defaultValue = "0") offset: Long
    ): ResponseEntity<List<Community>>{
        val communityList = communityService.selectCommunities(limit, offset)

        if(communityList.isEmpty()){
            throw CommunityException.NullCommunityException()
        }

        return ResponseEntity<List<Community>>(communityList, HttpStatus.OK)
    }

    @PostMapping
    fun insertCommunity(@RequestHeader jwt: String?, @RequestBody community: Community): ResponseEntity<String>{
        if(jwt == null){
            throw UserException.NotLoginException()
        }

        userService.selectUserByJwt(jwt) ?: throw UserException.NullUserException()



    }
}
