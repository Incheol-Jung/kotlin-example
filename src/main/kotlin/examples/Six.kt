package examples

/**
 *
 * @author Incheol.Jung
 * @since 2023. 09. 30.
 */
fun main() {
    var naverNull: String = "이 변수는 null이 될 수 없다"
//    naverNull = null

    var nullable: String? = "이 변수는 null이 될 수 있다"
    nullable = null

    var inferredNonNull = "컴파일러 타입추론은 null-불가로 본다"
//    inferredNonNull = null

    fun strLength(notNull: String): Int {
        return notNull.length
    }

    strLength(naverNull)
//    strLength(nullable)
}