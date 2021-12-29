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
                println("Coroutine $i is done [${Instant.now()}] [${Thread.currentThread().name}]")
            }
        }
        println("request: I'm done and I don't explicitly join my children that are still active [${Instant.now()}] [${Thread.currentThread().name}]")
    }
    request.join()
    println("Now processing of the request is complete [${Instant.now()}] [${Thread.currentThread().name}]")
}
