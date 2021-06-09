package dev.amaro.heroquest

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Test

class ItemTest {

    @Test
    fun `Weapon item has a name`() {
        assertThat(A.weapon(name = "Battle Axe").name).isEqualTo("Battle Axe")
    }

    @Test
    fun `Weapon item has an attack power`() {
        assertThat(A.weapon(power = 21).power).isEqualTo(21)
    }

    @Test
    fun `Armor item has a name`() {
        assertThat(A.armor(name = "Iron").name).isEqualTo("Iron")
    }

    @Test
    fun `Armor item has a defense power`() {
        assertThat(A.armor(power = 21).power).isEqualTo(21)
    }

}