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

}
fun giveMeATool(): Pair<String,Int>{
    return ("ten" to 10)
}