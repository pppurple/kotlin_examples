package com.example.coroutine.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() {
    GlobalScope.launch {
        delay(1_000L)
        println("[${Instant.now()}] World! [${Thread.currentThread().name}]")
    }
    println("[${Instant.now()}] Hello, [${Thread.currentThread().name}]")
    runBlocking {
        println("[${Instant.now()}] ...delay..., [${Thread.currentThread().name}]")
        delay(2_000L)
    }
}

