package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking

fun simple10(): Flow<Int> = flow {
    emit(1)
    throw RuntimeException()
}

fun main() = runBlocking {
    simple10()
        .onCompletion { cause ->
            if (cause != null) {
                log("Flow completed exceptionally")
            }
        }
        .catch { cause -> log("Caught exception. $cause") }
        .collect { value -> log(value) }
}
