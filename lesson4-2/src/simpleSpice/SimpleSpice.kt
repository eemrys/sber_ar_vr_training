package simpleSpice

class SimpleSpice {
    val name: String = "curry"
    val spiciness: String = "mild"
    val heat: Int
        get(): Int {
            return when(spiciness) {
                "mild" -> 5
                else -> 10
            }
        }
}