package toy.vote.communitiy.service

import org.springframework.stereotype.Service
import toy.vote.communitiy.datasource.community.entity.Community
import toy.vote.communitiy.enumclass.Response

@Service
class CommunityServiceImpl: CommunityService {
    override fun selectCommunities(limit: Int, offset: Long): List<Community> {
        TODO("Not yet implemented")
    }

    override fun insertCommunity(community: Community): Response {
        TODO("Not yet implemented")
    }
}
