package buildings

open class BaseBuildingMaterial { open val numberNeeded = 1 }
class Wood: BaseBuildingMaterial() { override val numberNeeded = 4 }
class Brick: BaseBuildingMaterial() { override val numberNeeded = 8 }

class Building<out T: BaseBuildingMaterial> (private val material: T) {
    private val baseMaterialsNeeded = 100
    fun actualMaterialsNeeded() = baseMaterialsNeeded * material.numberNeeded
    fun build() {
        println("${actualMaterialsNeeded()} of ${material::class.simpleName} required")
    }
}
fun <T: BaseBuildingMaterial> isSmallBuilding(building: Building<T>) {
    println(if (building.actualMaterialsNeeded() < 500) "small building" else "large building")
}
fun main(args: Array<String>) {
    val building = Building(Brick())
    building.build()
    isSmallBuilding(Building(Brick()))
}