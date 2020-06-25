package aquarium

fun main(args: Array<String>) {
    //buildAquarium()
    //makeFish()
    delegate()
}

fun buildAquarium(){
    val myAquarium = Aquarium()
    myAquarium.height = 80
    //println("Length: ${myAquarium.length} " + "Width: ${myAquarium.width} " + "Height: ${myAquarium.height}")
    //println("Volume: ${myAquarium.volume} liters")
    val myAquarium2 = Aquarium(numberOfFish = 9)
    println("Volume: ${myAquarium2.volume} liters")
    println("Length: ${myAquarium2.length} " + "Width: ${myAquarium2.width} " + "Height: ${myAquarium2.height}")
}
fun feedFish(fish: FishAction) {
    fish.eat()
}
fun makeFish() {
    val shark = Shark()
    val pleco = Plecostomus()
    println("Shark: ${shark.color} \nPlecostomus: ${pleco.color}")
    shark.eat()
    pleco.eat()
}

fun delegate() {
    val pleco = Plecostomus()
    println("color ${pleco.color}")
    pleco.eat()
}