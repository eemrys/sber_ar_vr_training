package buildings

open class BaseBuildingMaterial { open val numberNeeded = 1 }
class Wood: BaseBuildingMaterial() { override val numberNeeded = 4 }
class Brick: BaseBuildingMaterial() { override val numberNeeded = 8 }

class Building<T: BaseBuildingMaterial> (val material: T) {
    val baseMaterialsNeeded = 100
    fun actualMaterialsNeeded() = baseMaterialsNeeded * material.numberNeeded
    fun build() {
        println("${actualMaterialsNeeded()} of ${material::class.simpleName} required")
    }
}

fun main(args: Array<String>) {
    val building = Building(Wood())
    building.build()
}