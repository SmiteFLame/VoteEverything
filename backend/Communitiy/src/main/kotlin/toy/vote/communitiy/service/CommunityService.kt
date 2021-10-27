package toy.vote.communitiy.service

import org.springframework.data.domain.Page
import toy.vote.communitiy.datasource.community.entity.Community
import toy.vote.communitiy.datasource.community.util.CommunityInput
import toy.vote.communitiy.enumclass.Response
import toy.vote.communitiy.util.OffsetBasedPageRequest
import toy.vote.communitiy.wrapper.User

interface CommunityService {
    fun selectCommunityByCommunityId(communityId: Int): Community?
    fun insertCommunity(user: User, communityInput: CommunityInput): Response
}
