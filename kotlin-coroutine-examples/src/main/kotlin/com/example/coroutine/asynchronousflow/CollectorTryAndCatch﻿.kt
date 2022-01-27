package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun simple6(): Flow<Int> = flow {
    for (i in 1..3) {
        log("Emitting $i")
        emit(i) // emit next value
    }
}

fun main() = runBlocking {
    try {
        simple6().collect { value ->
            log(value)
            check(value <= 1) { "Collected $value" }
        }
    } catch (e: Throwable) {
        log("Caught $e")
    }
}
