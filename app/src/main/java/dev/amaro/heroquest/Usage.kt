package dev.amaro.heroquest

data class Usage(
    val startedAt: Round,
    val item: Item
) {
    fun isExpired(current: Round): Boolean {
        return current >= when (item.duration) {
            Duration.ThreeRounds -> 3
            Duration.FiveRounds -> 5
            else -> 0
        } + startedAt
    }
}

typealias Round = Int
