package dev.amaro.heroquest


import assertk.assertThat
import assertk.assertions.isFalse
import org.junit.Test


class BoardTest {

    @Test
    fun `Cannot go through walls`() {
        val board = Board()
        assertThat(board.canMove(Position(1, 1), Position(2, 2))).isFalse()
    }
}