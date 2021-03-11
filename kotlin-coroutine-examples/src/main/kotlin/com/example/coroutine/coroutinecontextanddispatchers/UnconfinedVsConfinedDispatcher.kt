package com.example.coroutine.coroutinecontextanddispatchers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() = runBlocking<Unit> {
    launch(Dispatchers.Unconfined) {
        println("[${Instant.now()}] Unconfined: I'm working in thread [${Thread.currentThread().name}]")
        delay(500)
        println("[${Instant.now()}] Unconfined: After delay in thread [${Thread.currentThread().name}]")
    }
    launch {
        println("[${Instant.now()}] main runBlocking: I'm working in thread [${Thread.currentThread().name}]")
        delay(500)
        println("[${Instant.now()}] main runBlocking: After delay in thread [${Thread.currentThread().name}]")
    }
}
