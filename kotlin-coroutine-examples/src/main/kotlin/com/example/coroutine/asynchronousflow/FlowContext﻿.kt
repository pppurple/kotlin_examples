package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun log(msg: String) = println("$msg [${Instant.now()}] [${Thread.currentThread().name}]")

fun simple2(): Flow<Int> = flow {
    log("Started simple flow")
    for (i in 1..3) {
        emit(i)
    }
}

fun main() = runBlocking {
    simple2().collect() { value -> log("Collected $value") }
}
