package aquarium

data class Fish(var name: String)

fun main(args: Array<String>) {
    //val myFish = listOf(Fish("Bob"), Fish("Jimmy"), Fish("Andy"))
    //println(myFish.filter { it.name.contains('y') }.joinToString(" ") { it.name })
    //fishExamples()

    val numbers = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
    println(numbers.divisibleBy { it.rem(3) })
}

fun fishExamples() {
    val myFish = Fish("Bob")

    myWith (myFish.name) {
        println(capitalize())
    }
    println(myFish.run { name })
    val myFish2 = Fish("Andy")
    println(myFish2.apply { name = "Jimmy" }) // returns object after lambda applied

    println(myFish.let { it.name.capitalize() } // it --> fish
        .let{it + "fish"} // it --> fish.name
        .let{it.length} // it --> new string
        .let{it + 31}) // it --> int (new string length)
}

fun myWith(name: String, block: String.() -> Unit) {
    name.block()
}

fun List<Int>.divisibleBy(block: (Int) -> Int): List<Int> {
    val result = mutableListOf<Int>()
    this.forEach {if (block(it) == 0) result.add(it)}
    return result
}