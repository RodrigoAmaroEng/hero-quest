package dev.amaro.heroquest

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import org.junit.Test

class UsageTest {

    @Test
    fun `Using an effect potion defines the start period`() {
        val hero = A.hero()
        val potion = Item.Potion.Effect.Defense("Invisible Shield", 32)
        hero.use(potion)
        assertThat(hero.using).contains(Usage(1, potion))
    }

    @Test
    fun `Using an attack effect potion increases attack for a moment`() {
        val hero = A.hero(attack = 10)
        val potion = Item.Potion.Effect.Attack("Minotaur Powder", 9)
        hero.use(potion)
        assertThat(hero.attackLevel).isEqualTo(19)
    }

    @Test
    fun `Attack effect potion can have a duration`() {
        val hero = A.hero(attack = 10)
        val potion = Item.Potion.Effect.Attack("Minotaur Powder", 9)
        hero.use(potion)
        repeat(3) { hero.nextRound() }
        assertThat(hero.attackLevel).isEqualTo(10)
    }

    @Test
    fun `Using a defense effect potion increases defense for a moment`() {
        val hero = A.hero(defense = 10)
        val potion = Item.Potion.Effect.Defense("Bull Powder", 9)
        hero.use(potion)
        assertThat(hero.defenseLevel).isEqualTo(19)
    }

    @Test
    fun `Using a healing potion only increase life points`() {
        val hero = A.hero(life = 10)
        val potion = Item.Potion.Healing("Energy Juice", 12)
        hero.use(potion)
        assertThat(hero.life).isEqualTo(22)
    }

    @Test
    fun `Using a magic potion only increase magic points`() {
        val hero = A.hero(magic = 10)
        val potion = Item.Potion.Magic("Dragon Tears", 12)
        hero.use(potion)
        assertThat(hero.magic).isEqualTo(22)
    }
}