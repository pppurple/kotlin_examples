package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun simpleCancellation(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(1000)
        log("Emitting $i.")
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    withTimeoutOrNull(2500) { // Timeout after 2500ms
        simpleCancellation().collect { value ->
            log(value)
        }
        log("Done")
    }
}
