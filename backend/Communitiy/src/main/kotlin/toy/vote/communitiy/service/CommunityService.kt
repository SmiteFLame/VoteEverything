package toy.vote.communitiy.service

import toy.vote.communitiy.datasource.community.entity.Community

interface CommunityService {
    fun selectCommunities(limit: Int, offset: Long): List<Community>
}
