package examples

/**
 *
 * @author Incheol.Jung
 * @since 2023. 10. 01.
 */
sealed class Mammal(val name: String)
class Cat(val catName: String) : Mammal(catName)
class Human(val humanName: String, val job: String) : Mammal(humanName)

fun greetMammal(mammal: Mammal) : String {
    when (mammal) {
        is Human ->
            return "안녕하세요, ${mammal.name}님, 직업은 ${mammal.job}이군요."
        is Cat ->
            return "안녕, ${mammal.name}"
    }
}

fun main() {
    println(greetMammal(Cat("Snowy")))
}