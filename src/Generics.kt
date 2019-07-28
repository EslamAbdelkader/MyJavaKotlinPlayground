import kotlin.random.Random

fun main() {
    val cars: List<Car> = listOf(Car(), Tesla())

//    val teslas : List<Tesla> = cars         // ERROR, List<Car> is not parent of List<Tesla>
    val objects: List<Object> = cars       // OK, List<Car> is parent of List<Object>


    val manager: Manager<out Car> = Manager(Tesla())

    manager.get()

//    manager.add(Car())            // ERROR, the add method now expects [Nothing], which means you can't use it

    val presenter = Presenter<Car>(Car())

    val another = Presenter<Car>(Tesla())

    val anotherPresenter = Presenter<Tesla>(Tesla())

//    val x = Presenter<Object>(Object()) // ERROR, can't create Presenter<Object>
}

open class Object

open class Car : Object()

class Tesla : Car()

class Producer<out T>(private vararg val data: T) {

    fun oneMore() = data[Random.nextInt(data.size - 1)]

//    fun add(x: T) = println()         // ERROR, type T is declared as 'out', can't be used as an 'in' (parameter)
}

class Manager<T>(private val t: T) {

    fun get() = t

    fun add(t: T) = println()
}

class Presenter<T : Car>(private val t: T) {
    fun get(): T = t
    fun add(t: T) = println()
}