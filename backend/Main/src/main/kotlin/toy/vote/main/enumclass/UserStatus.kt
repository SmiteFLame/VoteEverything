package toy.vote.main.enumclass

import java.io.Serializable

enum class UserStatus : Serializable {
    ACTIVE,
    INACTIVE;

    fun getStatus(): String {
        return this.name
    }
}
