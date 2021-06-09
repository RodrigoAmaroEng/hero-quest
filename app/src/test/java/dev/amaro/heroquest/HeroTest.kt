package dev.amaro.heroquest

import assertk.assertThat
import assertk.assertions.containsAll
import assertk.assertions.isEqualTo
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class HeroTest {
    @Test
    fun `Hero has attack points`() {
        assertThat(A.hero(attack = 24).attackLevel).isEqualTo(24)
    }

    @Test
    fun `Hero has defense points`() {
        assertThat(A.hero(defense = 23).defenseLevel).isEqualTo(23)
    }

    @Test
    fun `Hero has life points`() {
        assertThat(A.hero(life = 22).life).isEqualTo(22)
    }

    @Test
    fun `Hero has magic points`() {
        assertThat(A.hero(magic = 21).magic).isEqualTo(21)
    }

    @Test
    fun `Hero has a name`() {
        assertThat(A.hero(name = "Barbarian").name).isEqualTo("Barbarian")
    }

    @Test
    fun `Hero can collect and carry gold`() {
        val hero = A.hero()
        hero.collect(200)
        assertThat(hero.gold).isEqualTo(200)
    }

    @Test
    fun `Hero can have a weapon`() {
        val weapon = A.weapon()
        assertThat(A.hero(weapon = weapon).weapon).isEqualTo(weapon)
    }

    @Test
    fun `Hero can set a new weapon`() {
        val hero = A.hero()
        val weapon = A.weapon()
        hero.use(weapon)
        assertThat(hero.weapon).isEqualTo(weapon)
    }

    @Test
    fun `A weapon adds up to Hero attack points`() {
        val weapon = A.weapon(power = 5)
        assertThat(A.hero(attack = 3, weapon = weapon).attackLevel).isEqualTo(8)
    }

    @Test
    fun `Hero can have an Armor`() {
        val armor = A.armor()
        assertThat(A.hero(armor = armor).armor).isEqualTo(armor)
    }

    @Test
    fun `Hero can set a new Armor`() {
        val armor = A.armor()
        val hero = A.hero()
        hero.use(armor)
        assertThat(hero.armor).isEqualTo(armor)
    }

    @Test
    fun `An armor adds up to Hero defense points`() {
        val armor = A.armor(power = 5)
        assertThat(A.hero(defense = 3, armor = armor).defenseLevel).isEqualTo(8)
    }

    @Test
    fun `Hero can collect and carry Items`() {
        val weapon = A.weapon()
        val armor = A.armor()
        val hero = A.hero()
        hero.collect(weapon)
        hero.collect(armor)
        assertThat(hero.items).containsAll(weapon, armor)
    }
}

object A {
    fun hero(
        name: String = "",
        attack: Int = 0,
        defense: Int = 0,
        life: Int = 0,
        magic: Int = 0,
        weapon: Item.Weapon? = null,
        armor: Item.Armor? = null
    ) = Hero(name, attack, defense, life, magic, weapon, armor)

    fun weapon(name: String = "", power: Int = 0) =
        Item.Weapon(name, power)

    fun armor(name: String = "", power: Int = 0) =
        Item.Armor(name, power)
}