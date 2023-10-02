package examples

/**
 *
 * @author Incheol.Jung
 * @since 2023. 10. 02.
 */

fun findMinMax(list: List<Int>): Pair<Int, Int> {
    // do the math
    return Pair(50, 100)
}

data class User6(val username: String, val email: String)

fun getUser() = User6(username = "Mary", email = "mary@somewhere.com")

fun main() {
    val (x, y, z) = arrayOf(5, 10, 15)
    val map4 = mapOf("손석구" to 40, "장도연" to 38)
    for ((name, age) in map4) {
        println("${name}님은 올해 ${age}세 입니다")
    }

    val (min, max) = findMinMax(listOf(100, 90, 50, 80))
    println("min = $min, max = $max")

    val user = getUser()
    val (username, email) = user
    println(username == user.component1() )

    val (_1, emailAddress) = getUser()
    println("$_1, $emailAddress")
}
