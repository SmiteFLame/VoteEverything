package toy.vote.communitiy.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import toy.vote.communitiy.datasource.community.entity.Community
import toy.vote.communitiy.datasource.community.repository.CommunityRepository
import toy.vote.communitiy.datasource.community.util.CommunityInput
import toy.vote.communitiy.enumclass.Response
import toy.vote.communitiy.util.OffsetBasedPageRequest
import toy.vote.communitiy.wrapper.User
import java.sql.Timestamp

@Service
class CommunityServiceImpl: CommunityService {

    @Autowired
    lateinit var communityRepository: CommunityRepository

    override fun selectCommunities(offsetBasedPageRequest: OffsetBasedPageRequest): Page<Community> {
        return communityRepository.findAll(offsetBasedPageRequest)
    }

    override fun insertCommunity(user: User, communityInput: CommunityInput): Response {
        communityRepository.save(Community(
            email = user.email,
            name = user.name,
            title = communityInput.title,
            content = communityInput.content
        ))

        return Response.SUCCESS
    }
}
