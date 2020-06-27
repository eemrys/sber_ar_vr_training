package example

data class Fish(val name: String)

fun filterLambda() {
    val myFish = listOf(Fish("Flipper"), Fish("Moby Dick"), Fish("Dory"))
    println(myFish.filter { it.name.contains("i")}.joinToString(", ") { it.name })
    // filter() --> a higher-order function
}

fun fishExamples() {
    val fish = Fish("splashy")  // all lowercase
}

fun main() {
    fishExamples()
}