package examples

/**
 *
 * @author Incheol.Jung
 * @since 2023. 10. 02.
 */
data class Person3(var name: String, var age: Int, var about: String) {
    constructor() : this("", 0, "")
}

fun writeCreationLog(p : Person3) {
    println("${p.name} 인스턴스를 만들었습니다.")
}

fun main() {
    val jake = Person3(name = "Jake", age = 30, about = "안드로이드 개발자").also {
        writeCreationLog(it)
    }

}