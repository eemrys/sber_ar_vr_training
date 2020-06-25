package spices

sealed class Spice(val name: String, private val spiciness: String = "mild", color: SpiceColor):
                            SpiceColor by color {
    val heat: Int
        get(): Int {
            return when(spiciness) {
                "mild" -> 1
                "medium" -> 3
                "spicy" -> 5
                "very spicy" -> 7
                "extremely spicy" -> 10
                else -> 0
            }
        }
    init {
        println("Name: $name, Heat: $heat")
    }
    abstract fun prepareSpice()
}

class Curry(name: String, spiciness: String,
            color: SpiceColor = YellowSpiceColor): Spice(name, spiciness, color),
            Grinder {
    override fun grind() {
    }
    override fun prepareSpice() {
        grind()
    }
}
enum class Color(val rgb: Int) {
    RED(0xFF0000), GREEN(0x00FF00), BLUE(0x0000FF), YELLOW(0xFFFF00)
}

interface Grinder {
    fun grind()
}
interface SpiceColor {
    val color: Color
}
object YellowSpiceColor: SpiceColor {
    override val color = Color.YELLOW
}

data class SpiceContainer(val spice: Spice) {
    val label = spice.name
}