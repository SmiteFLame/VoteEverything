package toy.vote.communitiy.service

import toy.vote.communitiy.datasource.community.entity.Community
import toy.vote.communitiy.enumclass.Response

interface CommunityService {
    fun selectCommunities(limit: Int, offset: Long): List<Community>
    fun insertCommunity(community: Community): Response
}
