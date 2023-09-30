package examples

/**
 *
 * @author Incheol.Jung
 * @since 2023. 09. 30.
 */
class Customer
class Contact(val id: Int, var email: String)

fun main() {
    val customer = Customer()
    val contact = Contact(1, "incheol@kakao.co.kr")
    println(contact.email)
    contact.email = "incheol2@kakao.co.kr"
}