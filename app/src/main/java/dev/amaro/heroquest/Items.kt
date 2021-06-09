package dev.amaro.heroquest

sealed class Item(val name: String, val duration: Duration) {

    open fun useOn(hero: Hero, round: Round) {}

    class Weapon(name: String, val power: Int) : Item(name, Duration.Infinity) {
        override fun useOn(hero: Hero, round: Round) {
            hero.weapon = this
        }
    }

    class Armor(name: String, val power: Int) : Item(name, Duration.Infinity){
        override fun useOn(hero: Hero, round: Round) {
            hero.armor = this
        }
    }

    sealed class Potion(name: String, val amount: Int, duration: Duration) :
        Item(name, duration) {
        class Healing(name: String, amount: Int) : Potion(name, amount, Duration.UseAndDiscard) {
            override fun useOn(hero: Hero, round: Round) {
                hero.life += amount
            }
        }

        class Magic(name: String, amount: Int) : Potion(name, amount, Duration.UseAndDiscard) {
            override fun useOn(hero: Hero, round: Round) {
                hero.magic += amount
            }
        }

        sealed class Effect(name: String, amount: Int, duration: Duration) :
            Potion(name, amount, duration) {
            override fun useOn(hero: Hero, round: Round) {
                hero.using += Usage(round, this)
            }

            class Defense(name: String, amount: Int, duration: Duration = Duration.ThreeRounds) :
                Effect(name, amount, duration)

            class Attack(name: String, amount: Int, duration: Duration = Duration.ThreeRounds) :
                Effect(name, amount, duration)
        }
    }
}

sealed class Duration {
    object Infinity : Duration()
    object UseAndDiscard : Duration()
    object ThreeRounds : Duration()
    object FiveRounds : Duration()
}
