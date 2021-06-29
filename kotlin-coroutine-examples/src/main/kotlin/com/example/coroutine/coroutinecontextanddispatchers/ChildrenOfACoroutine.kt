package com.example.coroutine.coroutinecontextanddispatchers

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() = runBlocking {
    // launch a coroutine to process some kind of incoming request
    val request = launch {
        // it spawns two other jobs
        launch(Job()) {
            println("job1: I run in GlobalScope and execute independently! [${Instant.now()}] [${Thread.currentThread().name}]")
            delay(1000)
            println("job1: I am not affected by cancellation of the request [${Instant.now()}] [${Thread.currentThread().name}]")
        }

        // and the other inherits the parent context
        launch {
            delay(100)
            println("job2: I am a child of the request coroutine [${Instant.now()}] [${Thread.currentThread().name}]")
            delay(1000)
            println("job2: I will not execute this line if my parent request is cancelled [${Instant.now()}] [${Thread.currentThread().name}]")
        }
    }
    delay(500)
    request.cancel() // cancel processing of the request
    delay(1000) // delay a second to see what happens
    println("main: Who has survived request cancellation? [${Instant.now()}] [${Thread.currentThread().name}]")
}
