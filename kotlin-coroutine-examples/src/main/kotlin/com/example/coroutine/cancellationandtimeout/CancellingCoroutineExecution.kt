package com.example.coroutine.cancellationandtimeout

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() = runBlocking {
    val job = launch {
        repeat(1_000) { i ->
            println("job: I'm sleeping $i ... [${Instant.now()}] [${Thread.currentThread().name}]")
            delay(1_000L)
        }
    }
    delay(2_300L)
    println("main: I'm tired of waiting! [${Thread.currentThread().name}]")
    job.cancel()
    job.join()
    println("main: Now I can quit [${Thread.currentThread().name}]")
}
