package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun coldFlow(): Flow<Int> = flow {
    println("Flow started [${Instant.now()}] [${Thread.currentThread().name}]")
    for (i in 1..3) {
        delay(1000)
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    println("Calling coldFlow... [${Instant.now()}] [${Thread.currentThread().name}]")
    val flow = coldFlow()
    println("Calling collect... [${Instant.now()}] [${Thread.currentThread().name}]")
    flow.collect() { value ->
        println("$value [${Instant.now()}] [${Thread.currentThread().name}]")
    }
    println("Calling collect again... [${Instant.now()}] [${Thread.currentThread().name}]")
    flow.collect() { value ->
        println("$value [${Instant.now()}] [${Thread.currentThread().name}]")
    }
}
