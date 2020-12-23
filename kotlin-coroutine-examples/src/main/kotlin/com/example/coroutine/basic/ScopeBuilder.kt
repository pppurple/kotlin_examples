package com.example.coroutine.basic

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() = runBlocking {
    launch {
        delay(200L)
        println("[${Instant.now()}] Task from runBlocking [${Thread.currentThread().name}]")
    }

    coroutineScope {
        launch {
            delay(500L)
            println("[${Instant.now()}] Task from nested launch [${Thread.currentThread().name}]")
        }

        delay(100L)
        println("[${Instant.now()}] Task from coroutine scope [${Thread.currentThread().name}]")
    }

    println("[${Instant.now()}] Coroutine scope is over [${Thread.currentThread().name}]")
}
