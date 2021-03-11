package com.example.coroutine.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() = runBlocking {
    GlobalScope.launch {
        repeat(1_000) { i ->
            println("[${Instant.now()}] I'm sleeping $i ... [${Thread.currentThread().name}]")
            delay(500L)
        }
    }
    delay(1_300L)
}
