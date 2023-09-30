package examples

/**
 *
 * @author Incheol.Jung
 * @since 2023. 10. 01.
 */
class MutableStack<E>(vararg items: E) {
    private val elements = items.toMutableList()

    fun push(element: E) = elements.add(element)
    fun peek() : E = elements.last();
    fun pop() : E = elements.removeAt(elements.size - 1)
    fun isEmpty() = elements.isEmpty()
    fun size() = elements.size

    override fun toString(): String = "MutableStack(${elements.joinToString() }}"
}

fun main() {
    var stack = MutableStack(0.23, 0.4, 0.6)
    stack.push(9.87)
    println(stack)

    println("peek(): ${stack.peek()}")
    println(stack)

    for(i in 1..stack.size()){
        println("pop(): ${stack.pop()}")
        println(stack)
    }
}


