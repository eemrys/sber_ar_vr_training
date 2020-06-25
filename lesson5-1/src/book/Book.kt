package book

class Book(val title: String, val author: String, val year: String) {
    fun getPair(): Pair<String, String> {
        return (title to author)
    }
    fun getTriple(): Triple<String, String, String> {
        return Triple(title,author,year)
    }
}

fun main(args: Array<String>) {
    val book = Book("X", "Y", "Z")
    val (title, author, year) = book.getTriple()
    println("Here is your book $title written by $author in $year.")

    val allBooks = setOf("Macbeth", "Romeo and Juliet", "Hamlet", "A Midsummer Night's Dream")
    val library = mapOf(allBooks to "William Shakespeare")
    println(library.any {it.key.contains("Hamlet")})

    val moreBooks = mutableMapOf<String,String>("Some book" to "An author")
    moreBooks.getOrPut("Another book") { "Another author" }
    println(moreBooks)
}