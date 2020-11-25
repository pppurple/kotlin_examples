package com.example.coroutine.cancellationandtimeout

import kotlinx.coroutines.*

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) {
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ... [${Thread.currentThread().name}]")
                nextPrintTime += 500L
            }
        }
    }
    delay(1_300L)
    println("main: I'm tired of waiting! [${Thread.currentThread().name}]")
    job.cancelAndJoin()
    println("main: Now I can quit [${Thread.currentThread().name}]")
}
