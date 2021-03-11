package com.example.coroutine.coroutinecontextanddispatchers

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() = runBlocking<Unit> {
    launch(Dispatchers.Default + CoroutineName("test")) {
        println("[${Instant.now()}] I'm working in thread [${Thread.currentThread().name}]")
    }
}
