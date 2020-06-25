package simpleSpice

fun main(args: Array<String>) {
    val spices = listOf(
        Spice("curry", "mild"),
        Spice("pepper", "medium"),
        Spice("cayenne", "spicy"),
        Spice("ginger", "mild"),
        Spice("red curry", "medium"),
        Spice("green curry", "mild"),
        Spice("hot pepper", "extremely spicy")
    )
    val spiceList = spices.filter {it.heat < 5}
}

fun makeSalt() = Spice("Salt")