package examples

/**
 *
 * @author Incheol.Jung
 * @since 2023. 10. 01.
 */
fun main() {
    val authors = setOf("셰익스피어", "헤밍웨이", "트웨인")
    val writers = setOf("트웨인", "헤밍웨이", "셰익스피어")

    println(authors == writers)
    println(authors === writers)
}