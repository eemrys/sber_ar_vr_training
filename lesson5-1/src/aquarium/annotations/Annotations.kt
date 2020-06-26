package aquarium.annotations

import kotlin.reflect.full.findAnnotation

@ImAPlant class Plant {
    fun trim() {}
    fun fertilize() {}

    @get:OnGet
    val isGrowing: Boolean = true

    @set:OnSet
    var needsFood: Boolean = false
}

annotation class ImAPlant

@Target(AnnotationTarget.PROPERTY_GETTER)
annotation class OnGet

@Target(AnnotationTarget.PROPERTY_SETTER)
annotation class OnSet

fun reflections() {
    val classObj = Plant::class
    for (annotation in classObj.annotations) {
        println(annotation.annotationClass.simpleName)
    }
    val annotated = classObj.findAnnotation<ImAPlant>()
    annotated?.apply { println("found annotation") }
}