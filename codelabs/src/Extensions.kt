fun main(args: Array<String>) {
    println("Does it have spaces?".hasSpaces()) // --> true

    val plant = GreenLeafyPlant(size = 10)
    plant.print() // --> GreenLeafyPlant
    println("\n")
    val aquariumPlant: AquariumPlant = plant
    aquariumPlant.print()  // --> AquariumPlant bc of type

    val plant2: AquariumPlant? = null
    plant.pull() // --> no output
}

fun String.hasSpaces() = find { it == ' ' } != null

open class AquariumPlant(val color: String, private val size: Int)

class GreenLeafyPlant(size: Int) : AquariumPlant("green", size)

fun AquariumPlant.print() = println("AquariumPlant")
fun GreenLeafyPlant.print() = println("GreenLeafyPlant")
val AquariumPlant.isGreen: Boolean
    get() = color == "green"

fun AquariumPlant?.pull() { // nullable receiver
    this?.apply {
        println("removing $this")
    }
}
