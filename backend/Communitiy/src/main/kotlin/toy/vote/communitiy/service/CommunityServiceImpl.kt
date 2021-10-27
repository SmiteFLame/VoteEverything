package toy.vote.communitiy.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import toy.vote.communitiy.datasource.community.entity.Community
import toy.vote.communitiy.datasource.community.repository.CommunityRepository
import toy.vote.communitiy.enumclass.Response
import toy.vote.communitiy.util.OffsetBasedPageRequest

@Service
class CommunityServiceImpl: CommunityService {

    @Autowired
    lateinit var communityRepository: CommunityRepository

    override fun selectCommunities(offsetBasedPageRequest: OffsetBasedPageRequest): Page<Community> {
        return communityRepository.findAll(offsetBasedPageRequest)
    }

    override fun insertCommunity(community: Community): Response {
        TODO("Not yet implemented")
    }
}
