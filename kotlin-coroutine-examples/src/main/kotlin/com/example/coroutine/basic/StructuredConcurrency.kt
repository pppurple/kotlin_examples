package com.example.coroutine.basic

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() = runBlocking {
    launch {
        delay(1_000)
        println("[${Instant.now()}] World! [${Thread.currentThread().name}]")
    }
    println("[${Instant.now()}] Hello, [${Thread.currentThread().name}]")
}
