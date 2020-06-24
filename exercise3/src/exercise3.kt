import java.util.*

fun main(args: Array<String>) {
    //println(whatShouldIDoToday(readLine()!!))
    gamePlay(rollDice2(4))
}

fun whatShouldIDoToday(mood : String, weather : String = "sunny", temperature : Int = 24) : String {
    return when {
        goForWalk(mood, weather) -> "Go for a walk."
        stayInBed(mood, weather, temperature) -> "Stay in bed."
        goSwimming(temperature) -> "Go swimming."
        else -> "Stay home and read."
    }
}
fun stayInBed(mood : String, weather : String, temperature : Int) = mood == "sad" && weather == "rainy" && temperature == 0
fun goForWalk(mood : String, weather : String) = mood == "happy" && weather == "sunny"
fun goSwimming(temperature: Int) = temperature > 35

val rollDice = { Random().nextInt(12) + 1 }
val rollDice1 = {sides: Int -> if (sides == 0) 0 else Random().nextInt(sides) + 1}
val rollDice2: (Int) -> Int = {sides -> if (sides == 0) 0 else Random().nextInt(sides) + 1}
fun gamePlay(diceRoll: Int) {
    println(diceRoll)
}