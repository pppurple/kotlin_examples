package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun simple8(): Flow<Int> = flow {
    for (i in 1..3) {
        log("Emitting $i")
        emit(i)
    }
}

fun main() = runBlocking {
    simple8()
        .catch { e -> log("Caught $e") }
        .collect { value ->
            check(value <= 1) { "Collected $value" }
            log(value)
        }
}
