package toy.vote.communitiy.service

import org.springframework.data.domain.Page
import toy.vote.communitiy.datasource.community.entity.Community
import toy.vote.communitiy.enumclass.Response
import toy.vote.communitiy.util.OffsetBasedPageRequest

interface CommunityService {
    fun selectCommunities(offsetBasedPageRequest: OffsetBasedPageRequest): Page<Community>
    fun insertCommunity(community: Community): Response
}
