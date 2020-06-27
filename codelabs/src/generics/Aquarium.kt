package generics

open class WaterSupply(var needsProcessing: Boolean)

class TapWater : WaterSupply(true) {
    fun addChemicalCleaners() {
        needsProcessing = false
    }
}
class FishStoreWater : WaterSupply(false)

class LakeWater : WaterSupply(true) {
    fun filter() {
        needsProcessing = false
    }
}
class Aquarium<out T: WaterSupply>(val waterSupply: T) {
    fun addWater(cleaner: Cleaner<T>) {
        if (waterSupply.needsProcessing) {
            cleaner.clean(waterSupply)
        }
        println("water added")
    }
}

fun addItemTo(aquarium: Aquarium<WaterSupply>) = println("item added")

interface Cleaner<in T: WaterSupply> {
    fun clean(waterSupply: T)
}
class TapWaterCleaner : Cleaner<TapWater> {
    override fun clean(waterSupply: TapWater) = waterSupply.addChemicalCleaners()
}

fun genericsExample() {
    val aquarium = Aquarium(TapWater())
    println("water needs processing: ${aquarium.waterSupply.needsProcessing}")
    aquarium.waterSupply.addChemicalCleaners()
    println("water needs processing: ${aquarium.waterSupply.needsProcessing}")

    addItemTo(aquarium) // can call on Aquarium<TapWater> bc of "out"
    val cleaner = TapWaterCleaner()
    aquarium.addWater(cleaner) // can pass TapWaterCleaner bc of "in"

    isWaterClean(aquarium)
    println(aquarium.hasWaterSupplyOfType<TapWater>()) // --> true
    println(aquarium.waterSupply.isOfType<TapWater>()) // --> true
}

fun <T: WaterSupply> isWaterClean(aquarium: Aquarium<T>) {
    println("aquarium water is clean: ${!aquarium.waterSupply.needsProcessing}")
}
inline fun <reified R: WaterSupply> Aquarium<*>.hasWaterSupplyOfType() = waterSupply is R
inline fun <reified T: WaterSupply> WaterSupply.isOfType() = this is T

fun main() {
    genericsExample()
}