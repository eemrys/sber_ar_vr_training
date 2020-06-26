enum class Directions {
    NORTH, SOUTH, EAST, WEST, START, END
}

class Game {
    var path = mutableListOf<Directions>(Directions.START)
    private val north = { path.add(Directions.NORTH) }
    private val south = { path.add(Directions.SOUTH) }
    private val east = { path.add(Directions.EAST) }
    private val west = { path.add(Directions.WEST) }
    private val end = { path.add(Directions.END); println("Game Over: $path"); path.clear(); false}
    private fun move(where: () -> Boolean) {
        where.invoke()
    }
    fun makeMove(dir: String?) {
        when(dir) {
            "n" -> move(north)
            "s" -> move(south)
            "w" -> move(west)
            "e" -> move(east)
            else -> move(end)
        }
    }
}

fun main(args: Array<String>) {
    val game = Game()
    while(true) {
        print("Enter a direction: n/s/e/w:")
        game.makeMove(readLine())
    }
}