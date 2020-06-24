import java.util.*

fun main(args: Array<String>) {
    // println("${if (args[0].toInt() < 12) "Good morning, Kotlin" else "Good night, Kotlin"}")
    //feedTheFish()
    //println(canAddFish(10.0, listOf(), 7, true))
    var bubbles = 0
    while (bubbles < 50) {
        bubbles++
    }
    /*repeat(2) {
        println("A fish is swimming.")
    }*/
    //eagerExample()
    filterTest()
}
fun eagerExample() {
    val decorations = listOf("rock", "pagoda", "plastic plant", "alligator", "flowerpot")
    val eager = decorations.filter { it[0] == 'p' }
    //println(eager)
    val lazy = decorations.asSequence().filter { it[0]=='p' }
    //println(lazy.toList())

    val lazyMap = decorations.asSequence().map {
        println("map: $it")
        it
    }
    println(lazyMap)
    println("all: ${lazyMap.toList()}")
}

fun feedTheFish() {
    val day = randomDay()
    val food = fishFood(day)
    println("Today is $day and the fish eat $food.")

    if (shouldChangeWater(day)) {
        println("Change the water today.")
    }
    swim(50, speed="slow")
}

fun randomDay(): String {
    val week = listOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
    return week[Random().nextInt(7)]
}

fun fishFood(day : String) : String {
    return when (day) {
        "Monday" -> "flakes"
        "Tuesday" -> "pellets"
        "Wednesday" -> "worms"
        "Thursday" -> "flakes"
        "Friday" -> "granules"
        "Saturday" -> "mosquitoes"
        "Sunday" -> "plankton"
        else -> "fasting"
    }
}
fun swim(time : Int, speed : String = "fast") {
    println("Swimming $speed")
}
fun getDirtySensorReading() = 20

fun shouldChangeWater(day : String,
                      temperature : Int = 22,
                      dirty : Int = getDirtySensorReading()) : Boolean {
    return when {
        isTooHot(temperature) -> true
        isDirty(dirty) -> true
        isSunday(day) -> true
        else -> false
    }
}

fun isTooHot(temperature: Int) = temperature > 30
fun isDirty(dirty: Int) = dirty > 30
fun isSunday(day: String) = day == "Sunday"

fun canAddFish(tankSize : Double,
                currentFish : List<Int>,
                fishSize : Int = 2,
                hasDecorations : Boolean = true) : Boolean {
    return tankSize.times(if (hasDecorations) 0.8 else 1.0).minus(currentFish.sum()) >= fishSize
}

fun filterTest() {
    val spices = listOf("curry", "pepper", "cayenne", "ginger", "red curry", "green curry", "red pepper" )
    println(spices.filter { it.contains("curry") }.sortedBy { it.length })
    println(spices.filter { it.startsWith('c') && it.endsWith('e') })
    println(spices.take(3).filter { it.startsWith('c') })
}