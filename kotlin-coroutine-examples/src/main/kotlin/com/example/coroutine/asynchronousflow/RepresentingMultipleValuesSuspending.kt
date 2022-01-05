package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.time.Instant

suspend fun simpleSus(): List<Int> {
    delay(1000) // pretend we are doing something asynchronous here
    return listOf(1, 2, 3)
}

fun main() = runBlocking {
    simpleSus().forEach { value -> println("$value [${Instant.now()}] [${Thread.currentThread().name}]") }
}
