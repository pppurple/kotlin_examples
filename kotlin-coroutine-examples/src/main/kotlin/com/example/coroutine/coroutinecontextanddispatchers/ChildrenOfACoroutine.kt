package com.example.coroutine.coroutinecontextanddispatchers

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() = runBlocking {
    val request = launch {
        GlobalScope.launch {
            println("[${Instant.now()}] job1: I run in GlobalScope and execute independently! [${Thread.currentThread().name}]")
            delay(1000)
            println("[${Instant.now()}] job1: I am not affected by cancellation of the request [${Thread.currentThread().name}]")
        }

        launch {
            delay(100)
            println("[${Instant.now()}] job2: I am a child of the request coroutine [${Thread.currentThread().name}]")
            delay(1000)
            println("[${Instant.now()}] job2: I will not execute this line if my parent request is cancelled [${Thread.currentThread().name}]")
        }
    }
    delay(500)
    request.cancel()
    delay(1000)
    println("[${Instant.now()}] main: Who has survived request cancellation? [${Thread.currentThread().name}]")
}
