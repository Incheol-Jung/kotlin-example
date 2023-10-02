package examples

import java.lang.Math.abs
import java.util.NoSuchElementException

/**
 *
 * @author Incheol.Jung
 * @since 2023. 10. 01.
 */
fun main() {
    val numbers = listOf(1, -2, 3, -4, 5, -6)
    val positives = numbers.filter { x -> x > 0 } // 0 이상 리스트 -> [1,3,5]
    val negatives = numbers.filter { x -> x < 0 } // 0 이하 리스트 -> [-2,-4,-6]
    println(positives)
    println(negatives)

    val anyNegative = numbers.any { it < 0 } // 0이상이 존재하는가? -> true
    val anyGT6 = numbers.any { it > 6 } // 6초과가 존재하는가? -> false
    println(anyNegative)
    println(anyGT6)

    val allEven = numbers.all { it % 2 == 0 } // 모두가 짝수인가? -> false
    val allLess6 = numbers.all { it < 6 } // 모두 6이하인가? -> true
    println(allEven)
    println(allLess6)

    val noneEven = numbers.none { it % 2 == 1 } // 모두가 홀수가 아닌가? -> false
    val noneLess6 = numbers.none { it > 6 } // 모두가 6 초과가 아닌가? -> true
    println(noneEven)
    println(noneLess6)

    val words = listOf("컬렉션에", "있는", "어떤", "아이템", "골라서", "찾기", "아이스")

    val first = words.find { it.startsWith("아이") } // '아이'가 들어가는 단어를 리턴한다(검색 순서는 처음부터) -> 아이템
    val last = words.findLast { it.startsWith("아이") } // '아이'가 들어가는 단어를 리턴한다(검색 순서는 뒤에서 부터) -> 아이스
    val nothing = words.find { it.contains("없는") } // '없는'가 들어가는 단어를 리턴한다(검색 순서는 처음부터) -> null
    println(first)
    println(last)
    println(nothing)

    val firstNumber = numbers.first() // 가장 첫번째 숫자를 리턴한다
    val lastNumber = numbers.last() // 가장 마지막 숫자를 리턴한다
    println(firstNumber)
    println(lastNumber)

    val firstEven = numbers.first { it % 2 == 0 } // 짝수인 가장 첫번째 숫자를 리턴한다
    val lastOdd = numbers.last { it % 2 != 0 } // 홀수인 가장 마지막 숫자를 리턴한다
    println(firstEven)
    println(lastOdd)

    val words2 = listOf("foo", "bar", "baz", "faz")
    val empty = emptyList<String>()

    val first2 = empty.firstOrNull()
    val last2 = empty.lastOrNull()
    println(first2)
    println(last2)

    val firstF = words2.firstOrNull { it.startsWith('f') }
    val lastF = words2.lastOrNull { it.startsWith('z') }
    println(firstF)
    println(lastF)

    val totalCount = numbers.count()
    val evenCount = numbers.count { it % 2 == 0 }
    println(totalCount)
    println(evenCount)

    val doubled = numbers.map { x -> x * 2 }
    var tripled = numbers.map { x -> x * 3 }
    println(doubled)
    println(tripled)

    data class Person(val name: String, val city: String, val phone: String)

    val people = listOf(
        Person("석구", "서울", "02-1234-5678"),
        Person("성태", "부산", "031-234-9876"),
        Person("효리", "제주", "064-123-4567"),
        Person("상순", "제주", "064-234-8901")
    )

    val phoneBook = people.associateBy { it.phone }
    val cityBook = people.associateBy(Person::phone, Person::city)
    val peopleCities = people.groupBy(Person::city, Person::name)
    val lastPersonCity = people.associateBy(Person::city, Person::name)

    println(phoneBook)
    println(cityBook)
    println(peopleCities)
    println(lastPersonCity)

    val get2 = peopleCities.get("제주")
    println(get2?.size)
    val get = lastPersonCity.get("제주")
    println(get?.length)

    val evenOdd = numbers.partition { it % 2 == 0 }
    val (positives2, negatives2) = numbers.partition { it > 0 }
    println(evenOdd)
    println(positives2)
    println(negatives2)

    val fruitsBag = listOf("사과", "오렌지", "바나나")
    val clothesBag = listOf("티셔츠", "양말", "청바지")
    val cart = listOf(fruitsBag, clothesBag)
    val mapBag = cart.map { it }
    val flatMapBag = cart.flatMap { it }
    println(mapBag)
    println(flatMapBag)

    val numbers2 = listOf(1, 2, 3)
    val empty2 = emptyList<Int>()
    val only = listOf(3)

    println("숫자들: $numbers2, 최소 = ${numbers2.minOrNull()} 최대 = ${numbers2.maxOrNull()}")
    println("숫자들: $empty2, 최소 = ${empty2.minOrNull()} 최대 = ${empty2.maxOrNull()}")
    println("숫자들: $only, 최소 = ${only.minOrNull()} 최대 = ${only.maxOrNull()}")

    val shuffled = listOf(5, 4, 2, 1, 3, -10)
    val natural = shuffled.sorted()
    val inverted = shuffled.sortedBy { -it }
    val descending = shuffled.sortedDescending()
    val descendingBy = shuffled.sortedByDescending { abs(it) }

    println(natural)
    println(inverted)
    println(descending)
    println(descendingBy)

    val map = mapOf("키" to 42)
    val value1 = map["키"]
    val value2 = map["키2"]

    val value3 = map.getValue("키")

    val mapWithDefault = map.withDefault { k -> k.length }
    val value4 = mapWithDefault.getValue("키2")

    println(value1)
    println(value2)
    println(value3)
    println(value4)

    try {
        map.getValue("없는 키")
    } catch (e: NoSuchElementException) {
        println("메시지 : $e")
    }

    val A = listOf("a", "b", "c")
    val B = listOf(1, 2, 3, 4)

    val resultPairs = A zip B
    val resultReduce = A.zip(B) { a, b -> "$a$b" }

    println(resultPairs)
    println(resultReduce)

    val list3 = listOf(0, 10, 20)
    println(list3.getOrElse(1) { 42 })
    println(list3.getOrElse(10) { 42 })

    val map3 = mutableMapOf<String, Int?>()
    println(map3.getOrElse("x") { 1 })
    println(map3.getOrDefault("x", 1))

    map3["x"] = 3
    println(map3.getOrElse("x") { 1 })

    map3["x"] = null
    println(map3.getOrElse("x") { 1 })

}