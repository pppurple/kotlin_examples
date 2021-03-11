package com.example.coroutine.coroutinecontextanddispatchers

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() = runBlocking {
    val request = launch {
        repeat(3) { i ->
            launch {
                delay((i + 1) * 200L)
                println("[${Instant.now()}] Coroutine $i is done [${Thread.currentThread().name}]")
            }
        }
        println("[${Instant.now()}] request: I'm done and I don't explicitly join my children that are still active [${Thread.currentThread().name}]")
    }
    request.join()
    println("[${Instant.now()}] Now processing of the request is complete [${Thread.currentThread().name}]")
}
