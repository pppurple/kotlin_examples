package com.example.coroutine.basic

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() = runBlocking {
    launch { doWorld() }
    println("[${Instant.now()}] Hello, [${Thread.currentThread().name}]")
}

suspend fun doWorld() {
    delay(1_000L)
    println("[${Instant.now()}] World! [${Thread.currentThread().name}]")
}
