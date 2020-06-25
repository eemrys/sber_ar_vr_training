package aquarium.generics

open class WaterSupply(var needsProcessed: Boolean)
class TapWater: WaterSupply(true) {
    fun addChemicalCleaners() {
        needsProcessed = false
    }
}

class FishStoreWater: WaterSupply(false)
class LakeWater: WaterSupply(true) {
    fun filter() {
        needsProcessed = false
    }
}

class Aquarium<out T: WaterSupply>(val waterSupply: T) {
    fun addWater(cleaner: Cleaner<T>) {
        if (waterSupply.needsProcessed) {
            cleaner.clean(waterSupply)
        }
        println("adding water from ${waterSupply::class.simpleName}")
    }
}
interface Cleaner<in T: WaterSupply> {
    fun clean(waterSupply: T)
}
class TapWaterCleaner: Cleaner<TapWater> {
    override fun clean(waterSupply: TapWater) {
        waterSupply.addChemicalCleaners()
    }
}
fun addItem(aquarium: Aquarium<WaterSupply>) = println("item added")
fun <T: WaterSupply> isWaterClean(aquarium: Aquarium<T>) {
    println("water is clean: ${aquarium.waterSupply.needsProcessed}")
}
inline fun <reified R: WaterSupply> Aquarium<*>.hasWaterSupplyOfType() = waterSupply is R
inline fun <reified T: WaterSupply> WaterSupply.isOfType() = this is T
fun genericExample() {
    val aquarium = Aquarium(TapWater())
    aquarium.waterSupply.addChemicalCleaners()

    val aquarium2 = Aquarium(LakeWater())
    aquarium2.waterSupply.filter()

    val cleaner = TapWaterCleaner()
    aquarium.addWater(cleaner)

    isWaterClean(aquarium)
    println(aquarium.hasWaterSupplyOfType<TapWater>())
    println(aquarium2.waterSupply.isOfType<LakeWater>())
}

fun main(args: Array<String>) {
    genericExample()
}