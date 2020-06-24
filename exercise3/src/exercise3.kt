fun main(args: Array<String>) {
    println(whatShouldIDoToday(readLine()!!))
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