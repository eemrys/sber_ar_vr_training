package book

import java.util.*

const val MAX_BOOKS = 10
class Book(val title: String, val author: String, val year: String, var pages: Int) {
    companion object {
        const val BASE_URL = "BASE_URL"
    }
    fun getPair(): Pair<String, String> {
        return (title to author)
    }
    fun getTriple(): Triple<String, String, String> {
        return Triple(title,author,year)
    }
    fun canBorrow(num: Int): Boolean{
        return (MAX_BOOKS > num)
    }
    fun printUrl() {
        println("$BASE_URL/$title.html")
    }
}

fun Book.weight(): Double {
    return (pages * 1.5)
}
fun Book.tornPages(num: Int) {
    if (pages >= num) pages -= num
    else pages = 0
}

class Puppy {
    fun playWithBook(book: Book) {
        book.tornPages(Random().nextInt(15))
    }
}
fun main(args: Array<String>) {
    val book = Book("X", "Y", "Z", 150)
    val (title, author, year) = book.getTriple()
    println("Here is your book $title written by $author in $year.")

    val allBooks = setOf("Macbeth", "Romeo and Juliet", "Hamlet", "A Midsummer Night's Dream")
    val library = mapOf(allBooks to "William Shakespeare")
    println(library.any {it.key.contains("Hamlet")})

    val moreBooks = mutableMapOf<String,String>("Some book" to "An author")
    moreBooks.getOrPut("Another book") { "Another author" }
    println(moreBooks)

    book.printUrl()

    val puppy = Puppy()
    while (book.pages > 0) {
        puppy.playWithBook(book)
        println("${book.pages} left in ${book.title}")
    }
}