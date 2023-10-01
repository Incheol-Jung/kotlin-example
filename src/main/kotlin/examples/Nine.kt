package examples

/**
 *
 * @author Incheol.Jung
 * @since 2023. 10. 01.
 */

fun main() {
    cases("Hello")
    cases(1)
    cases(0L)
    cases(MyClass())
    cases("hello")

    println(whenAssign("안녕"))
    println(whenAssign(3.4))
    println(whenAssign(1))
    println(whenAssign(MyClass()))
}

fun cases(obj: Any) {
    when (obj) {
        1 -> println("하나")
        "Hello" -> println("인사")
        is Long -> println("Long")
        !is String -> println("문자열 아님")
        else -> println("그 외")
    }
}

fun whenAssign(obj: Any): Any {
    val result = when (obj) {
        1 -> "하나"
        "안녕" -> 1
        is Long -> false
        else -> 42
    }

    return result
}

class MyClass