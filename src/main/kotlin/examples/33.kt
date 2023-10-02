package examples

/**
 *
 * @author Incheol.Jung
 * @since 2023. 10. 02.
 */
fun format(userName: String, domain: String) = "$userName@$domain"

fun main() {
    println(format("mario", "example.com"))
    println(format("domain.com", "username"))
    println(format(userName = "foo", domain = "username"))
    println(format(domain = "frog.com", userName = "pepe"))
}