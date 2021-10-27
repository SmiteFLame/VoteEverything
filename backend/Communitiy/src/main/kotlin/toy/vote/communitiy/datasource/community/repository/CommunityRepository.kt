package toy.vote.communitiy.datasource.community.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import toy.vote.communitiy.datasource.community.entity.Community

interface CommunityRepository : JpaRepository<Community, String> {
    fun findCommunityByCommunityId(communityId: Int): Community?

    fun findCommunitiesByCommunityIdContaining(communityId: Int, pageable: Pageable): Page<Community>
    fun findCommunitiesByTitleContaining(title: String, pageable: Pageable): Page<Community>
    fun findCommunitiesByNameContaining(name: String, pageable: Pageable): Page<Community>
    fun findCommunitiesByContentContaining(content: String, pageable: Pageable): Page<Community>
}
