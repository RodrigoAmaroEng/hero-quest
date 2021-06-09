package dev.amaro.heroquest

import java.util.function.Predicate

data class Hero(
    val name: String,
    val attackBase: Int,
    val defenseBase: Int,
    var life: Int,
    var magic: Int,
    var weapon: Item.Weapon?,
    var armor: Item.Armor?
) {
    private var round = 1
    var gold: Int = 0
        private set
    val attackLevel
        get() = attackBase
            .plus(weapon?.power ?: 0)
            .plus(
                using
                    .filter { it.item is Item.Potion.Effect.Attack }
                    .sumBy { (it.item as Item.Potion).amount }
            )
    val defenseLevel
        get() = defenseBase
            .plus(armor?.power ?: 0)
            .plus(
                using
                    .filter { it.item is Item.Potion.Effect.Defense }
                    .sumBy { (it.item as Item.Potion).amount }
            )
    val items: Bag = Bag()

    fun collect(gold: Int) {
        this.gold += gold
    }

    fun collect(item: Item) {
        this.items += item
    }

    fun use(potion: Item) {
        potion.useOn(this, round)
    }

    fun nextRound() {
        round++
        using.removeAll { it.isExpired(round) }
    }

    val using: MutableList<Usage> = mutableListOf()

    inner class Bag : Iterable<Item> {
        operator fun plusAssign(item: Item) {
            put(item)
        }

        private fun put(item: Item) {
            container += item
        }

        fun take(item: Item) {
            container -= item
        }

        private val container: MutableList<Item> = mutableListOf()
        override fun iterator(): Iterator<Item> = container.iterator()
    }
}