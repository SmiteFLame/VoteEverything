package toy.vote.communitiy.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import toy.vote.communitiy.datasource.community.entity.Community
import toy.vote.communitiy.datasource.community.repository.CommunityRepository
import toy.vote.communitiy.datasource.community.util.CommunityInput
import toy.vote.communitiy.enumclass.Response
import toy.vote.communitiy.exception.CommunityException
import toy.vote.communitiy.exception.UserException
import toy.vote.communitiy.service.CommunityService
import toy.vote.communitiy.service.UserService
import toy.vote.communitiy.util.OffsetBasedPageRequest

@RestController
@CrossOrigin(origins = ["*"], maxAge = 6000)
@RequestMapping("/community")
class CommunityController {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var communityService: CommunityService

    @Autowired
    lateinit var communityRepository: CommunityRepository

    @GetMapping
    fun selectCommunities(
        @RequestParam(defaultValue = "10") limit: Int,
        @RequestParam(defaultValue = "0") offset: Long,
        @RequestParam map: Map<String, String>
    ): ResponseEntity<Page<Community>> {
        val offsetBasedPageRequest =
            OffsetBasedPageRequest(limit, offset, map["sort-by"], map["order-by"], "communityId")

        val communityList = if (map.containsKey("communityId")) {
            communityRepository.findCommunitiesByCommunityIdContaining(map["communityId"]!!.toInt(), offsetBasedPageRequest)
        } else if (map.containsKey("title")) {
            communityRepository.findCommunitiesByTitleContaining(map["title"]!!, offsetBasedPageRequest)
        } else if (map.containsKey("name")) {
            communityRepository.findCommunitiesByNameContaining(map["name"]!!, offsetBasedPageRequest)
        } else if (map.containsKey("content")) {
            communityRepository.findCommunitiesByContentContaining(map["content"]!!, offsetBasedPageRequest)
        } else {
            communityRepository.findAll(offsetBasedPageRequest)
        }

        return ResponseEntity<Page<Community>>(communityList, HttpStatus.OK)
    }

    @GetMapping("/{community_id}")
    fun selectCommunityByCommunityId(@PathVariable(name = "community_id") communityId: Int): ResponseEntity<Community> {
        val community = communityService.selectCommunityByCommunityId(communityId)
            ?: throw CommunityException.NullCommunityException()
        return ResponseEntity<Community>(community, HttpStatus.OK)
    }

    @PostMapping
    fun insertCommunity(
        @RequestHeader jwt: String?,
        @RequestBody communityInput: CommunityInput
    ): ResponseEntity<Response> {

        if (jwt == null) {
            throw UserException.NotLoginException()
        }

        val user = userService.selectUserByJwt(jwt) ?: throw UserException.NullUserException()

        return ResponseEntity<Response>(communityService.insertCommunity(user, communityInput), HttpStatus.CREATED)
    }
}
