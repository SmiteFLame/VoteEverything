package toy.vote.main.cache

import java.time.LocalDateTime

data class Cache<T>(
    val data: T,
    val time: LocalDateTime
){
    fun check(now : LocalDateTime) : Boolean{
        if(time.year < now.year){
            return false
        } else if(time.dayOfYear < now.dayOfYear){
            return false
        }
        return true
    }
}
