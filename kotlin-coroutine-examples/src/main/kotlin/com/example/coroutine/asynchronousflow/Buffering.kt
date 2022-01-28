package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import java.time.Instant
import kotlin.system.measureTimeMillis

fun simple5(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100) // pretend we are asynchronously waiting 100 ms
        emit(i) // emit next value
    }
}

/*
fun main() = runBlocking {
    val time = measureTimeMillis {
        simple5().collect { value ->
            delay(300) // pretend we are processing it for 300 ms
            log(value)
        }
    }
    log("Collected in $time ms")
}
*/

// use `buffer()`
fun main() = runBlocking {
    val time = measureTimeMillis {
        simple5().buffer()
            .collect { value -> // buffer emissions, don't wait
                delay(300) // pretend we are processing it for 300 ms
                log(value)
            }
    }
    log("Collected in $time ms")
}

fun log(msg: Int) = println("$msg [${Instant.now()}] [${Thread.currentThread().name}]")
