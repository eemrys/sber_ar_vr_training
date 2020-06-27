package example

fun labels() {
    outerLoop@ for (i in 1..100) {
        print("$i ")
        for (j in 1..100) {
            if (i > 10) break@outerLoop  // breaks to outer loop
        }
    }
}

fun regularLoop() {
    for (i in 1..100) {
        print("$i ")
        for (j in 1..100) {
            if (i > 10) break
        }
    }
}

fun main() {
    labels() // --> 1 2 3 4 5 6 7 8 9 10 11
    print("\n") // --> 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21.......
    regularLoop()
}