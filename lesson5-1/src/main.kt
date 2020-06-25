fun main(args: Array<String>) {
    val symptoms = mutableListOf("white spots", "red spots", "not eating", "belly up", "bloated")
    symptoms.add("white fungus")
    symptoms.remove("white spots")
    symptoms.contains("red") // false
    symptoms.contains("red spots") // true
    println(symptoms.subList(4, symptoms.size)) // [white fungus]

    listOf(1,2,3).sum() // 6
    listOf("a","b","cc").sumBy { it.length } // 4

    val cures = mapOf("white spots" to "Ich", "red sores" to "hole disease") // key -> value
    println(cures["red sores"])
    println(cures.getOrDefault("red spots", "sorry idk"))

    cures.getOrElse("red spots") { println("No cure for this.") }
    val inventory = mutableMapOf("fish net" to 1)
    inventory.put("tank scrubber", 3)
    inventory.remove("fish tank")
    val foo = Constants.CONSTANT2
    println("Do I have spaces?".hasSpaces())

}
fun giveMeATool(): Pair<String,Int>{
    return ("ten" to 10)
}

object Constants {
    const val CONSTANT2 = "object constant"
}

class ClassWithConstants {
    companion object {
        const val CONSTANT3 = "constant inside companion"
    }
}

fun String.hasSpaces(): Boolean = find { it == ' '} != null

class MyList<T> {
    fun get(pos: Int) {}
    fun addItem(item: T) {}
}

fun workWithGenericList() {
    val strList: MyList<String>
}