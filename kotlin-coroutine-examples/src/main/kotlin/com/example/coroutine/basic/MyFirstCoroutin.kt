package com.example.coroutine.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Instant

fun main() {
    GlobalScope.launch {
        delay(1_000L)
        println("[${Instant.now()}] World! [${Thread.currentThread().name}]")
    }
    println("[${Instant.now()}] Hello, [${Thread.currentThread().name}]")
    Thread.sleep(2_000L)
}
