package aquarium

abstract class AquariumFish: FishAction {
    abstract val color: String
    override fun eat() = println("abstract eating")
}

class Shark: AquariumFish(), FishAction {
    override val color = "grey"
    override fun eat(){
        println("shark is eating")
    }
}
class Plecostomus(fishColor: FishColor = GoldColor):
    FishAction by PrintingFishAction("pleco food"),
    FishColor by fishColor

interface FishAction {
    fun eat()
}
interface FishColor {
    val color: String
}

object GoldColor: FishColor {
    override val color = "gold"
}
object RedColor: FishColor {
    override val color = "red"
}

class PrintingFishAction(val food: String): FishAction {
    override fun eat() {
        println(food)
    }
}