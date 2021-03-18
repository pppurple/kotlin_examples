package com.example.coroutine.cancellationandtimeout

import kotlinx.coroutines.*
import java.time.Instant

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(1_000) { i ->
                println("job: I'm sleeping $i ... [${Instant.now()}] [${Thread.currentThread().name}]")
                delay(1_000L)
            }
        } finally {
            withContext(NonCancellable) {
                println("job: I'm running finally. [${Instant.now()}] [${Thread.currentThread().name}]")
                delay(1_000L)
                println("job: And I've just delayed for 1 sec because I'm non-cancelable. [${Instant.now()}] [${Thread.currentThread().name}]")
            }
        }
    }
    delay(2_300L) // delay a bit
    println("main: I'm tired of waiting! [${Thread.currentThread().name}]")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit [${Thread.currentThread().name}]")
}
