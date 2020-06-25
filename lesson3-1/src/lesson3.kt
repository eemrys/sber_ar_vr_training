import java.util.*

fun main(args: Array<String>) {
    println("Hello, ${args[0]}")
    val temp = 10
    val isHot = if (temp > 50) true else false
    println(isHot)
    val message = "You are a ${if (isHot) "fried" else "safe"} fish"
    println(message)
}

fun dayOfWeek(){
    when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
        1 -> println("Sunday")
        2 -> println("Monday")
        3 -> println("Tuesday")
        4 -> println("Wednesday")
        5 -> println("Thursday")
        6 -> println("Friday")
        7 -> println("Saturday")
    }
}