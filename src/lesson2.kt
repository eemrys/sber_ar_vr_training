fun main() {
    val b: Byte = 1
    val i: Int = b.toInt()
    //println(i)
    val oneMil = 1_000_000
    //println(oneMil)
    var rocks: Int? = null
    var lotsOfFish: List<String?> = listOf(null,null,null)
    var moreFish: List<String>? = null
    var evenMoreFish: List<String?>? = null
    val fish = 2
    //println(fish.plus(71).plus(233).minus(13).div(30))
    var rainbowColor = "black"
    rainbowColor = "white"
    //println(rainbowColor)
    var greenColor = null
    var blueColor: Int? = null
    var list1 = listOf(null,null)
    var list2: List<Int?> = listOf(null, null)
    var list3: List<Int>? = null
    var nullTest: Int? = null
    //println(nullTest?.inc() ?:0)
    val trout = "trout"
    var haddock = "haddock"
    var snapper = "snapper"
    //println("I have no idea what $trout, $haddock, or $snapper is.")
    val fishName = "bob"
    when(fishName.length){
        0 -> println("Fish name cannot be empty")
        in 3..12 -> println("Good fish name")
        else -> println("OK fish name")
    }
    when(fishName){
        "bob" -> println("it's bob")
        else -> println("go away")
    }
    var numbers = Array(5){it+11}
    var emptyList = mutableListOf<String>()
    // OR var emptyList : MutableList<String> = mutableListOf()
    for (el in numbers) {
        emptyList.add(el.toString())
    }
    println(emptyList)
    var emptyListOfInt: MutableList<Int> = mutableListOf()
    for (i in 0..100 step 7) emptyListOfInt.add(i)
    print(emptyListOfInt)
}