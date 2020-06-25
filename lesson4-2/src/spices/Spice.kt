package spices

abstract class Spice(val name: String, private val spiciness: String = "mild", color: SpiceColor):
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

interface Grinder {
    fun grind()
}
interface SpiceColor {
    val color: String
}
object YellowSpiceColor: SpiceColor {
    override val color = "yellow"
}