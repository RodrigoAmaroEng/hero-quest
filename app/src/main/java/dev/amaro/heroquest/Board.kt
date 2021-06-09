package dev.amaro.heroquest


// Boards have 26 x 19 tiles
class Board {

    private val tiles: List<Tile> = emptyList()

    fun canMove(point: Position, point1: Position): Boolean {
        TODO("Not yet implemented")
    }
}

data class Tile(
    val position: Position,
    val adjacent: List<Position>
)

data class Position(val x: Int, val y: Int)