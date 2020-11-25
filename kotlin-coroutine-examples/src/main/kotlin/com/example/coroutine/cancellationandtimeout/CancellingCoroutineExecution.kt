package com.example.coroutine.cancellationandtimeout

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        repeat(1_000) { i ->
            println("job: I'm sleeping $i ... [${Thread.currentThread().name}]")
            delay(500L)
        }
    }
    delay(1_300L)
    println("main: I'm tired of waiting! [${Thread.currentThread().name}]")
    job.cancel()
    job.join()
    println("main: Now I can quit [${Thread.currentThread().name}]")
}
