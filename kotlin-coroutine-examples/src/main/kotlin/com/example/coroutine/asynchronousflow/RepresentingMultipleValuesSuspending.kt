package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.time.Instant

suspend fun simpleSus(): List<Int> {
    delay(1000)
    return listOf(1, 2, 3)
}

fun main() = runBlocking {
    simpleSus().forEach { value -> println("[${Instant.now()}] $value") }
}
