package toy.vote.main.cache

import toy.vote.main.datasource.vote.util.VoteOutput

class CacheCollection {
    companion object {
        var voteOutputBest : Cache<List<VoteOutput>>? = null
    }
}
