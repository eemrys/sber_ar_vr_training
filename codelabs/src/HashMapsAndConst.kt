fun main(args: Array<String>) {
    mutableMapExample()
}

fun hashMapExample() {
    val cures = hashMapOf("white spots" to "Ich", "red sores" to "hole disease")
    println(cures.get("white spots")) // two ways to get value by key
    println(cures["red sores"])
    println(cures["scale loss"]) // not in the map --> null
    println(cures.getOrDefault("bloating", "sorry, I don't know")) // returns string
    println(cures.getOrElse("bloating") {"No cure for this"}) // executes code
}

fun mutableMapExample() {
    val inventory = mutableMapOf("fish net" to 1)
    inventory["tank scrubber"] = 3 // OR inventory.put("tank scrubber", 3)
    println(inventory.toString())
    inventory.remove("fish net")
    println(inventory.toString())
}

object Constants { // created when first used
    const val CONSTANT = "object constant"
}


class MyClass {
    companion object { // created when the object is created
        const val CONSTANT2 = "constant in companion"
    }
}