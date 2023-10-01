package examples

/**
 *
 * @author Incheol.Jung
 * @since 2023. 10. 01.
 */
const val POINTS_X_PASS: Int = 15
val EZPassAccounts: MutableMap<Int, Int> = mutableMapOf(1 to 100, 2 to 100, 3 to 100)
val EZPassReport: Map<Int, Int> = EZPassAccounts

fun updatePointsCredit(accountId: Int) {
    if (EZPassAccounts.containsKey(accountId)){
        println("업데이트 $accountId")
        EZPassAccounts[accountId] = EZPassAccounts.getValue(accountId) + POINTS_X_PASS
    } else {
        println("에러: 계정이 없습니다 (id: $accountId")
    }
}

fun accountsReport() {
    println("EZ-Pass 현황:")
    EZPassReport.forEach{
        k,v -> println("ID $k: 포인트 $v")
    }
}

fun main() {
    accountsReport()
    updatePointsCredit(1)
    updatePointsCredit(1)
    updatePointsCredit(5)
    accountsReport()
}