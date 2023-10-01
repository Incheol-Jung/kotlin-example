package examples

/**
 *
 * @author Incheol.Jung
 * @since 2023. 10. 01.
 */
fun main() {
    for (i in 0..3) {
        print(i)
    }
    print(" ")

    for (i in 0 until 3) {
        print(i)
    }
    print(" ")

    for (i in 2..8 step 2) {
        print(i)
    }
    print(" ")

    for (i in 3 downTo 0) {
        print(i)
    }

    println()

    for (c in 'a'..'d') {
        print(c)
    }
    print(" ")

    for (c in 'z' downTo 's' step 2) {
        print(c)
    }
    println()

    val x = 2
    if (x in 1..5) {
        println("x는 1에서 5 사이의 수")
    }

    if (x !in 6..10) {
        print("x는 6에서 10 사이의 수가 아님")
    }
}