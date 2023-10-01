package examples

/**
 *
 * @author Incheol.Jung
 * @since 2023. 10. 01.
 */
data class Item(val name: String, val price: Int)
data class Order(val items: Collection<Item>)

fun Order.maxPricedItemValue(): Int = this.items.maxByOrNull { it.price }?.price ?: 0
fun Order.maxPricedItemName() = this.items.maxByOrNull { it.name }?.name ?: ""

val Order.commaDeimitedItemNames: String
    get() = items.map { it.name }.joinToString()

fun main() {
    val order = Order(listOf(Item("빵", 5000), Item("와인", 29000), Item("생수", 1500)))

    println("가장 비싼 식료춤 : ${order.maxPricedItemName()}")
    println("가장 비산 가격: ${order.maxPricedItemValue()}")
    println("식료품: ${order.commaDeimitedItemNames}")
}