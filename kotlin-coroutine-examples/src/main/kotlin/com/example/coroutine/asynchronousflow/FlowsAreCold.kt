package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun coldFlow(): Flow<Int> = flow {
    log("Flow started")
    for (i in 1..3) {
        delay(1000)
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    log("Calling coldFlow...")
    val flow = coldFlow()
    log("Calling collect...")
    flow.collect { value ->
        log(value)
    }
    log("Calling collect again...")
    flow.collect { value ->
        log(value)
    }
}
