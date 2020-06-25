package aquarium

class Fish(val friendly: Boolean = true, volumeNeeded: Int) {
    val size: Int
    init {
        println("first init block")
    }
    init {
        size = if (friendly) {
            volumeNeeded
        } else {
            volumeNeeded * 2
        }
    }
    constructor(): this(true, 9) {
        println("running secondary constructor")
    }
}
fun makeDefaultFish() = Fish(true, 50) //helper function instead of sec. const.