package example

import kotlin.reflect.full.*

annotation class ImAPlant // annotation created

@Target(AnnotationTarget.PROPERTY_GETTER)
annotation class OnGet
@Target(AnnotationTarget.PROPERTY_SETTER)
annotation class OnSet

@ImAPlant class Plant {
    @get:OnGet
    val isGrowing: Boolean = true

    @set:OnSet
    var needsFood: Boolean = false
}

fun testAnnotations() {
    val plantObject = Plant::class
    val myAnnotationObject = plantObject.findAnnotation<ImAPlant>()
    println(myAnnotationObject)
}

fun main() {
    testAnnotations()
}