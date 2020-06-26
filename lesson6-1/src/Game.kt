import kotlin.math.absoluteValue

enum class Directions {
    NORTH, SOUTH, EAST, WEST, START, END
}

class Game {
    var path = mutableListOf<Directions>(Directions.START)
    var map = Location()
    private val north = { path.add(Directions.NORTH); }
    private val south = { path.add(Directions.SOUTH); }
    private val east = { path.add(Directions.EAST); }
    private val west = { path.add(Directions.WEST); }
    private val end = { path.add(Directions.END); println("Game Over: $path"); path.clear(); false}
    private fun move(where: () -> Boolean) {
        where.invoke()
    }
    fun makeMove(dir: String?) {
        when(dir) {
            "n" -> { move(north); map.updateLocation(Directions.NORTH)}
            "s" -> { move(south); map.updateLocation(Directions.SOUTH)}
            "e" -> {move(east); map.updateLocation(Directions.EAST)}
            "w" -> {move(west); map.updateLocation(Directions.WEST)}
            else -> {move(end); map.updateLocation(Directions.END)}
        }
    }
}

class Location (private val width: Int = 4, private val height: Int = 4) {
    val map = Array(width) { arrayOfNulls<String>(height) }
    var currentLocation = Pair (0,0)
    fun updateLocation(direction: Directions) {
        currentLocation = when(direction) {
            Directions.NORTH -> Pair(currentLocation.first, (currentLocation.second + 1).rem(height))
            Directions.SOUTH -> Pair(currentLocation.first, (currentLocation.second - 1).absoluteValue.rem(height))
            Directions.EAST -> Pair((currentLocation.first + 1).rem(width), currentLocation.second)
            Directions.WEST -> Pair((currentLocation.first - 1).absoluteValue.rem(width), currentLocation.second)
            else -> Pair(0,0)
        }
    }
    fun getDescription(): String? {
        return map[currentLocation.first.rem(width)][currentLocation.second.rem(height)]
    }

    init {
        map[0][0] = "You are at (0,0). Enter a direction - n/e: "
        map[0][1] = "You are at (0,1). Enter a direction - n/s/e: "
        map[0][2] = "You are at (0,2). Enter a direction - n/s/e: "
        map[0][3] = "You are at (0,3). Enter a direction - s/e: "

        map[1][0] = "You are at (1,0). Enter a direction - n/e/w: "
        map[1][1] = "You are at (1,1). Enter a direction - n/s/e/w: "
        map[1][2] = "You are at (1,2). Enter a direction - n/s/e/w: "
        map[1][3] = "You are at (1,3). Enter a direction - s/e/w: "

        map[2][0] = "You are at (2,0). Enter a direction - n/e/w: "
        map[2][1] = "You are at (2,1). Enter a direction - n/s/e/w: "
        map[2][2] = "You are at (2,2). Enter a direction - n/s/e/w: "
        map[2][3] = "You are at (2,3). Enter a direction - s/e/w: "

        map[3][0] = "You are at (3,0). Enter a direction - n/w: "
        map[3][1] = "You are at (3,1). Enter a direction - n/s/w: "
        map[3][2] = "You are at (3,2). Enter a direction - n/s/w: "
        map[3][3] = "You are at (3,3). Enter a direction - s/w: "
    }
}

fun main(args: Array<String>) {
    val game = Game()
    while(true) {
        print(game.map.getDescription())
        game.makeMove(readLine())
    }
}