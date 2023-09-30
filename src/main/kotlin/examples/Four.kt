package examples

/**
 *
 * @author Incheol.Jung
 * @since 2023. 09. 30.
 */
fun main() {
    operator fun Int.times(str: String) = str.repeat(this)
    println(2 * "하이 ")

    operator fun String.get(range: IntRange) = substring(range)
    val str = "웃어라! 온 세상이 너와 함께 웃을 것이다"
    println(str[0..3])

    fun printAll(vararg messages: String) {
        for(m in messages) println(m)
    }
    printAll("Hello", "Hallo", "Salut", "Hola")

    fun printAllWithPrefix(vararg message : String, prefix : String) {
        for(m in message) println(prefix + m)
    }
    printAllWithPrefix("Hello", "Hallo", "Salut", "Hola", prefix = "Greeting")

    fun log(vararg entries: String) {
        printAll(*entries)
    }

}