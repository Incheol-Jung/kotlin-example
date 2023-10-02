package examples

/**
 *
 * @author Incheol.Jung
 * @since 2023. 10. 02.
 */
data class Person2(var name: String, var age: Int, var about: String) {
    constructor() : this("", 0, "")
}

fun main() {
    val jake = Person2()
    val stringDescription = jake.apply {
        name = "Jake"
        age = 30
        about = "안드로이드 개발자"
    }.toString()

    println(stringDescription)
}