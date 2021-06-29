package com.example.coroutine.coroutinecontextanddispatchers

import kotlinx.coroutines.*
import java.time.Instant

@ObsoleteCoroutinesApi
fun main() = runBlocking<Unit> {
    launch {
        // context of the parent, main runBlocking coroutine
        println("main runBlocking: I'm working in thread [${Thread.currentThread().name}] [${Instant.now()}]")
    }
    launch(Dispatchers.Unconfined) {
        // not confined -- will work with main thread
        println("Unconfined: I'm working in thread [${Thread.currentThread().name}] [${Instant.now()}]")
    }
    launch(Dispatchers.Default) {
        // will get dispatched to DefaultDispatcher
        println("Default: I'm working in thread [${Thread.currentThread().name}] [${Instant.now()}]")
    }
    launch(newSingleThreadContext("MyOwnThread")) {
        // will get its own new thread
        println("newSingleThreadContext: I'm working in thread [${Thread.currentThread().name}] [${Instant.now()}]")
    }
}
