package pseudo01

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.log

fun main() = runBlocking {
    delayHelloWorld()
}

suspend fun delayHelloWorld() {
    log("Hello")
    delay(2000L)
    log("World!")
}
