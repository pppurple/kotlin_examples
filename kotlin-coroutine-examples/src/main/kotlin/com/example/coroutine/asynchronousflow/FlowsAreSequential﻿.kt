package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() = runBlocking {
    (1..5).asFlow()
        .filter {
            println("Filter $it [${Instant.now()}] [${Thread.currentThread().name}]")
            it % 2 == 0
        }
        .map {
            println("Map $it [${Instant.now()}] [${Thread.currentThread().name}]")
            "string $it"
        }
        .collect {
            println("Collect $it [${Instant.now()}] [${Thread.currentThread().name}]")
        }
}
