package generics

// when a type parameter T of a class C is declared out, C<Base> can safely be a supertype of C<Derived>.

open class BaseClass(val randomNumber: Int = 10)

class DerivedClass(randomNumber: Int): BaseClass(randomNumber)

class OutExample<out T: BaseClass>(val number: T)

// when a type parameter T of a class C is declared in, C<Derived> can safely be a supertype of C<Base>.

class InExample<in T: BaseClass> {
    fun calc(number: T) {
        println(number.randomNumber * 2)
    }
}

fun main() {
    val example1 = OutExample(DerivedClass(5))
    val example2: OutExample<BaseClass> = example1

    val example3 = InExample<BaseClass>()
    val example4: InExample<DerivedClass> = example3
}