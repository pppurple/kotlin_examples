package com.example.coroutine.cancellationandtimeout

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(1_000) { i ->
                println("job: I'm sleeping $i ... [${Instant.now()}] [${Thread.currentThread().name}]")
                delay(1_000L)
            }
        } finally {
            println("job: I'm running finally. [${Instant.now()}] [${Thread.currentThread().name}]")
        }
    }
    delay(2_300L) // delay a bit
    println("main: I'm tired of waiting! [${Thread.currentThread().name}]")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit [${Thread.currentThread().name}]")
}
