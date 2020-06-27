package example

data class Fish(var name: String)

fun filterLambda() {
    val myFish = listOf(Fish("Flipper"), Fish("Moby Dick"), Fish("Dory"))
    println(myFish.filter { it.name.contains("i")}.joinToString(", ") { it.name })
    // filter() --> a higher-order function
}

fun fishExamples() {
    val fish = Fish("splashy")  // all lowercase
    myWith (fish.name) {    // with myWith() inline, this becomes a direct call: fish.name.capitalize()
        println(capitalize())
    }

    // returns result of the lambda
    fish.run {
        name
    }

    //returns the changed object
    val fish2 = Fish(name = "splashy").apply {
        name = "sharky"
    }
    println(fish2)

    // returns a copy of the object with the changes
    println(fish.let { it.name.capitalize()}
        .let{it + "fish"}
        .let{it.length}
        .let{it + 31})
    println(fish) // the obj itself has not changed
}

inline fun myWith(name: String, block: String.() -> Unit) {
    name.block()
}

fun main() {
    fishExamples()
}