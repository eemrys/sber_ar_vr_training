package aquarium

open class AquariumPlant(val color: String, private val size: Int)
class GreenPlant(size: Int): AquariumPlant("green", size)
fun AquariumPlant.isRed() = color == "red"
fun AquariumPlant.print() = println("Aquarium plant")
fun GreenPlant.print() = println("Green plant")
val AquariumPlant.isGreen: Boolean
    get() = color == "green"

fun AquariumPlant?.pull() {
    this?.apply { println("removing $this") }
}

fun staticExample() {
    val plant = GreenPlant(60)
    plant.print() // Green plant
    val aquariumPlant: AquariumPlant = plant
    aquariumPlant.print() // Aquarium plant --> bc type
}

fun propertyExample() {
    val plant = AquariumPlant("green", 50)
    println(plant.isGreen)
}

fun nullableExample() {
    val plant: AquariumPlant? = null
    plant.pull()
}