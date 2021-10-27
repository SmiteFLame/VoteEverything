package toy.vote.communitiy.datasource.community.repository

import org.springframework.data.jpa.repository.JpaRepository
import toy.vote.communitiy.datasource.community.entity.Community

interface CommunityRepository : JpaRepository<Community, String> {
    fun findCommunityByCommunityId(communityId: Int): Community?
}
