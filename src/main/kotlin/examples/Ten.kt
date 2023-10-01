package examples

/**
 *
 * @author Incheol.Jung
 * @since 2023. 10. 01.
 */
fun main() {
    val cakes = listOf("당근", "치즈", "초콜릿")
    for (cake in cakes) {
        println("맛있는 ${cake}케이크!")
    }

    var cakesEaten = 0
    var cakesBaked = 0

    while (cakesEaten < 5) {
        eatACake()
        cakesEaten++;
    }

    do {
        bakeACake()
        cakesBaked++
    } while (cakesBaked < cakesEaten)

    val zoo = Zoo(listOf(Animal("얼룩말"), Animal("사자")))

    for (animal in zoo) {
        println("여기 ${animal.name} 있어요!")
    }
}

fun eatACake() = println("케이크 먹기")
fun bakeACake() = println("케이크 굽기")

class Animal(val name: String)
class Zoo(val animals: List<Animal>) {
    operator fun iterator(): Iterator<Animal> {
        return animals.iterator()
    }
}

