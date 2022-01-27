package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun simple7(): Flow<String> =
    flow {
        for (i in 1..3) {
            log("Emitting $i")
            emit(i) // emit next value
        }
    }.map { value ->
        check(value <= 1) { "Crashed on $value" }
        "string $value"
    }

fun main() = runBlocking {
    try {
        simple7().collect { value -> log(value) }
    } catch (e: Throwable) {
        log("Caught $e")
    }
}
