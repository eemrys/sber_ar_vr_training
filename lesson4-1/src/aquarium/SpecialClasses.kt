package aquarium

object MobyDickWhale {
    val author = "Herman Melville"
    fun jump() {
        //...
    }
}

enum class Color(val rgb: Int){
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0X0000FF)
}

sealed class Seal // can only be subclassed inside current file
class Sealion: Seal()
class Walrus: Seal()

fun matchSeal(seal: Seal): String {
    return when(seal) {
        is Sealion -> "sea lion"
        is Walrus -> "walrus"
    }
}