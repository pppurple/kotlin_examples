package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun simpleFlow(): Flow<Int> = flow { // flow builder
    for (i in 1..3) {
        delay(500) // pretend we are doing something useful here
        // If use Thread.sleep, the main thread is blocked
        // Thread.sleep(500)
        emit(i) // emit next value
    }
}

fun main() = runBlocking {
    // Launch a concurrent coroutine to check if the main thread is blocked
    launch {
        for (k in 1..3) {
            println("I'm not blocked $k [${Instant.now()}] [${Thread.currentThread().name}]")
            delay(500)
        }
    }
    // Collect the flow
    simpleFlow().collect { value -> println("$value [${Instant.now()}] [${Thread.currentThread().name}]") }
}
