package com.example.coroutine.coroutinecontextanddispatchers

import kotlinx.coroutines.*
import java.time.Instant

@ObsoleteCoroutinesApi
fun main() = runBlocking<Unit> {
    launch {
        println("main runBlocking: I'm working in thread [${Thread.currentThread().name}] [${Instant.now()}]")
    }
    launch(Dispatchers.Unconfined) {
        println("Unconfined: I'm working in thread [${Thread.currentThread().name}] [${Instant.now()}]")
    }
    launch(Dispatchers.Default) {
        println("Default: I'm working in thread [${Thread.currentThread().name}] [${Instant.now()}]")
    }
    launch(newSingleThreadContext("MyOwnThread")) {
        println("newSingleThreadContext: I'm working in thread [${Thread.currentThread().name}] [${Instant.now()}]")
    }
}
