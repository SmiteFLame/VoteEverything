package toy.vote.communitiy.service

import org.springframework.stereotype.Service
import toy.vote.communitiy.datasource.community.entity.Community

@Service
class CommunityServiceImpl: CommunityService {
    override fun selectCommunities(limit: Int, offset: Long): List<Community> {
        TODO("Not yet implemented")
    }
}
