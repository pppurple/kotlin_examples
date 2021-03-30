package com.example.coroutine.cancellationandtimeout

import kotlinx.coroutines.*
import java.time.Instant

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (isActive) { // cancellable computation loop
            // print a message once a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ... [${Instant.now()}] [${Thread.currentThread().name}]")
                nextPrintTime += 1_000L
            }
        }
    }
    delay(2_300L) // delay a bit
    println("main: I'm tired of waiting! [${Thread.currentThread().name}]")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit [${Thread.currentThread().name}]")
}
