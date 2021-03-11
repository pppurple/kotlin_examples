package com.example.coroutine.cancellationandtimeout

import kotlinx.coroutines.*
import java.time.Instant

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (isActive) {
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ... [${Instant.now()}] [${Thread.currentThread().name}]")
                nextPrintTime += 1_000L
            }
        }
    }
    delay(2_300L)
    println("main: I'm tired of waiting! [${Thread.currentThread().name}]")
    job.cancelAndJoin()
    println("main: Now I can quit [${Thread.currentThread().name}]")
}
