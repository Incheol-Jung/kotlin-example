package examples

/**
 *
 * @author Incheol.Jung
 * @since 2023. 10. 02.
 */
fun main() {
    fun getNullableLength(ns: String?) {
        println("대상 \"$ns\":")
        ns?.run {
            println("\t비었나? => " + isEmpty())
            println("\t길이 => $length")
            length
        }
    }

    getNullableLength(null)
    getNullableLength("")
    getNullableLength("코틀린에서 어떤 문자열")
}