package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() = runBlocking {
    // Convert an integer range to a flow
    (1..3).asFlow().collect { value ->
        println("$value [${Instant.now()}] [${Thread.currentThread().name}]")
    }
}
