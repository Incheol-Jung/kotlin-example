package examples

/**
 *
 * @author Incheol.Jung
 * @since 2023. 10. 01.
 */
fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
    return operation(x, y)
}

fun sum2(x: Int, y: Int) = x + y

fun main() {
    val sumResult = calculate(4, 5, ::sum2)
    val mulResult = calculate(4, 5) { a, b -> a * b }
    println("sumResult ${sumResult}, mulResult $mulResult")

    val func = operation()
    println(func(2))
}

fun operation(): (Int) -> Int {
    return ::square
}

fun square(x:Int) = x*x