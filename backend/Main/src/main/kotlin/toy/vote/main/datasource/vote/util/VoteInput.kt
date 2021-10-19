package toy.vote.main.datasource.vote.util

data class VoteInput(
    var voteId: Int? = null,
    val email: String,
    val time: Long,
    val voteName: String,
    val voteColumn: List<VoteColumnInput>
)
