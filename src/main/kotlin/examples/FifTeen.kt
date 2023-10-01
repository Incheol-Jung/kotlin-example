package examples

/**
 *
 * @author Incheol.Jung
 * @since 2023. 10. 01.
 */
data class User(val name: String, val id: Int) {
    override fun equals(other: Any?) = other is User && other.id == this.id
}

fun main() {
    val user = User("석구", 1)
    println(user)

    val secondUser = User("석구", 1)
    val thirdUser = User("동석", 2)

    println("user == secondUser : ${user == secondUser}")
    println("user == thirdUser : ${user === thirdUser}")

    println(user.hashCode())
    println(secondUser.hashCode())
    println(thirdUser.hashCode())

    // copu()
    println(user.copy())
    println(user === user.copy())
    println(user.copy("동석"))
    println(user.copy(id = 3))

    println("name = ${user.component1()}")                 // 10
    println("id = ${user.component2()}")
}
