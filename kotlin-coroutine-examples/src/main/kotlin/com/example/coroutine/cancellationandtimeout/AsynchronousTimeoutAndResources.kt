package com.example.coroutine.cancellationandtimeout

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import java.time.Instant

var acquired = 0

class Resource {
    init {
        acquired++ // Acquire the resource
    }

    fun close() = acquired-- // Release the resource
}

fun main() {
    runBlocking {
        repeat(100_000) { // Launch 100K coroutines
            launch {
                val resource = withTimeout(60) { // Timeout of 60 ms
                    delay(50) // Delay for 50 ms
                    Resource() // Acquire a resource and return it from withTimeout block
                }
                resource.close() // Release the resource
            }
        }
    }

    // not always print zero
    // Outside of runBlocking all coroutines have completed
    println("$acquired [${Instant.now()}] [${Thread.currentThread().name}]") // Print the number of resources still acquired
}
