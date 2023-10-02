package examples

/**
 *
 * @author Incheol.Jung
 * @since 2023. 10. 02.
 */
fun customPrint(s: String) {
    print(s.uppercase())
}
fun main() {
    val empty = "test".let {
        customPrint(it)
        it.isEmpty()
    }
    println(" 비었나: $empty")

    fun printNonNull(str: String?) {
        println("\"$str\" 프린트:")

        str?.let{
            print("\t")
            customPrint(it)
            println()
        }

    }

    fun printIfBothNonNull(strOne: String?, strTwo: String?) {
        strOne?.let {firstString ->
            strTwo?.let {secondString ->
                customPrint("$firstString : $secondString")
                println()
            }
        }
    }

    printNonNull(null)
    printNonNull("문자열")
    printIfBothNonNull("첫번째", "두번째")
}

