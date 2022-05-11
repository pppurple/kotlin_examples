package pseudo01

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    delayHelloWorld()
}

suspend fun delayHelloWorld() {
    println("Hello")
    delay(1000L)
    println("World!")
}
