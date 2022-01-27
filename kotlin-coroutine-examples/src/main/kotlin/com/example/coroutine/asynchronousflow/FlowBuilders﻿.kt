package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    // Convert an integer range to a flow
    (1..3).asFlow().collect { value ->
        log(value)
    }
}
