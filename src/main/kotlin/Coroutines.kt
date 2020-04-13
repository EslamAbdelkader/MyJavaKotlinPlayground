import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
        launch {
        print(1)
            printAsync(2)
            print(3)
    }
    try {
        failedConcurrentSum()
    } catch (e: ArithmeticException) {
        println("Computation failed with ArithmeticException")
        e.printStackTrace()
        GlobalScope
    }

}

suspend fun failedConcurrentSum(): Int = coroutineScope {
    val one = async<Int> {
        try {
            delay(Long.MAX_VALUE) // Emulates very long computation
            42
        } catch (e: Exception) {
            e.printStackTrace()
            42
        } finally {
            println("First child was cancelled")
        }
    }
    val two = async<Int> {
        println("Second child throws an exception")
        throw ArithmeticException()
    }
    one.await() + two.await()
}

suspend fun printAsync(int: Int) {
    delay(1000)
    print(int)
}

fun print(int: Int) {
    println("${Thread.currentThread().name} - $int")
}