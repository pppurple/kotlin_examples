package com.example.coroutine.cancellationandtimeout

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import java.time.Instant

fun main() = runBlocking {
    val result = withTimeoutOrNull(2_300L) {
        repeat(1_000) { i ->
            println("I'm sleeping $i ... [${Instant.now()}] [${Thread.currentThread().name}]")
            delay(1_000L)
        }
        "Done"
    }
    println("Result is $result. [${Instant.now()}] [${Thread.currentThread().name}]")
}
