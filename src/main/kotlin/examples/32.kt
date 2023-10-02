package examples

import kotlin.reflect.KProperty

/**
 *
 * @author Incheol.Jung
 * @since 2023. 10. 02.
 */
class Example {
    var p: String by Delegate()

    override fun toString(): String = "예제 클래스"
}

class Delegate() {
    operator fun getValue(thisRef: Any?, prop: KProperty<*>): String {
        return "$thisRef, '${prop.name}' 속성을 위임했습니다"
    }

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: String) {
        println("$thisRef 인스턴스에 있는 ${prop.name} 속성으로 지정된 값은 ${value} 입니다")
    }
}

class LazySample {
    init {
        println("created!")
    }

    val lazyStr: String by lazy {
        println("계산!!")
        "지연 값"
    }
}

class User4(val map: MutableMap<String, Any?>) {
    val name: String by map
    var age: Int by map
}

fun main() {
    val e = Example()
    println(e.p)
    e.p = "NEW"

    val sample = LazySample()
    println("lazyStr = ${sample.lazyStr}")
    println("lazyStr = ${sample.lazyStr}")

    val user = User4(
        mutableMapOf(
        "name" to "John Doe",
        "age" to 25
    )
    )

    println("name = ${user.name}, age = ${user.age}")
    user.age = 33
    println("name = ${user.name}, age = ${user.age}")
}