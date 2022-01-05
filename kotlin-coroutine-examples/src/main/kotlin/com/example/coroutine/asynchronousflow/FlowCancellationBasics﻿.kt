package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import java.time.Instant

fun simpleCancellation(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(1000)
        println("Emitting $i. [${Instant.now()}] [${Thread.currentThread().name}]")
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    withTimeoutOrNull(2500) { // Timeout after 2500ms
        simpleCancellation().collect { value ->
            println("$value [${Instant.now()}] [${Thread.currentThread().name}]")
        }
        println("Done [${Instant.now()}] [${Thread.currentThread().name}]")
    }
}
