package com.example.coroutine.coroutinecontextanddispatchers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() = runBlocking<Unit> {
    launch(Dispatchers.Unconfined) {
        // not confined -- will work with main thread
        println("Unconfined: I'm working in thread [${Instant.now()}] [${Thread.currentThread().name}]")
        delay(500)
        println("Unconfined: After delay in thread [${Instant.now()}] [${Thread.currentThread().name}]")
    }
    launch {
        // context of the parent, main runBlocking coroutine
        println("main runBlocking: I'm working in thread [${Instant.now()}] [${Thread.currentThread().name}]")
        delay(1000)
        println("main runBlocking: After delay in thread [${Instant.now()}] [${Thread.currentThread().name}]")
    }
}
