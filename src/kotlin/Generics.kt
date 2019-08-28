package kotlin

import kotlin.random.Random

fun main() {
    val cars: List<Car> = listOf(Car(), Tesla())

//    val teslas : List<kotlin.Tesla> = cars         // ERROR, List<kotlin.Car> is not parent of List<kotlin.Tesla>
    val objects: List<Object> = cars       // OK, List<kotlin.Car> is parent of List<kotlin.Object>


    val manager: Manager<out Car> = Manager(Tesla())

    manager.get()

//    manager.add(kotlin.Car())            // ERROR, the add method now expects [Nothing], which means you can't use it

    val presenter = Presenter<Car>(Car())

    val another = Presenter<Car>(Tesla())

    val anotherPresenter = Presenter<Tesla>(Tesla())

//    val x = kotlin.Presenter<kotlin.Object>(kotlin.Object()) // ERROR, can't create kotlin.Presenter<kotlin.Object>
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