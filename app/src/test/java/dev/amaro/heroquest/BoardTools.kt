package dev.amaro.heroquest


import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.streams.toList

object BoardTools {
    private const val MAX_X = 26
    private const val MAX_Y = 19
    private const val SQUARE = 15

    fun generateEmptyBoard() {
        (1..MAX_X).forEach { x ->
            (1..MAX_Y).forEach { y ->
                val line = StringBuilder()
                line.append("$x,$y")
                if (x > 1) line.append(":${x - 1},$y")
                if (y > 1) line.append(":$x,${y - 1}")
                if (x < MAX_X) line.append(":${x + 1},$y")
                if (y < MAX_Y) line.append(":$x,${y + 1}")
                println(line)
            }
        }
    }

    fun loadBoard(): List<Tile> {
        return File("/Users/rodrigo.amaro/Projects/HeroQuest/app/src/main/assets/board.map")
            .bufferedReader()
            .lines()
            .toList()
            .map { it.split(':') }
            .map { Tile(it[0].toPoint(), it.drop(1).map { it -> it.toPoint() }) }
    }

//    fun drawMap(map: List<Tile>, canvas: Canvas) {
//        val paint: Paint = Paint(Color.BLACK)
//        (1..MAX_X).forEach { x ->
//            (1..MAX_Y).forEach { y ->
//                val square = Rect(
//                    x * SQUARE,
//                    y * SQUARE,
//                    x * SQUARE + SQUARE,
//                    y * SQUARE + SQUARE
//                )
//                canvas.drawRect(square, paint)
//            }
//        }
//    }
}

fun String.toPoint(): Position {
    return split(',').let {
        Position(it[0].toInt(), it[1].toInt())
    }
}

fun main(args: Array<String>) {
    val map = BoardTools.loadBoard()
//    val bitmap = Bitmap.createBitmap(28 * SQUARE, 21 * SQUARE, Bitmap.Config.ALPHA_8)
//    BoardTools.drawMap(map, Canvas(bitmap))
//    try {
//        FileOutputStream().use { out ->
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out) // bmp is your Bitmap instance
//        }
//    } catch (e: IOException) {
//        e.printStackTrace()
//    }
}