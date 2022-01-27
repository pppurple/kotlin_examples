package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    (1..5).asFlow()
        .filter {
            log("Filter $it")
            it % 2 == 0
        }
        .map {
            log("Map $it")
            "string $it"
        }
        .collect {
            log("Collect $it")
        }
}
