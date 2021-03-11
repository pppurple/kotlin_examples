package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun simpleFlow(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(500)
        // If use Thread.sleep, the main thread is blocked
        // Thread.sleep(500)
        emit(i)
    }
}

fun main() = runBlocking {
    launch {
        for (k in 1..3) {
            println("[${Instant.now()}] I'm not blocked $k")
            delay(500)
        }
    }
    simpleFlow().collect { value -> println("[${Instant.now()}] $value") }
}
