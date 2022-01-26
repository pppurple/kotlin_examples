package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    simple9()
        .onCompletion { cause -> log("Flow completed with $cause") }
        .collect { value ->
            check(value <= 1) { "Collected $value" }
            log(value)
        }
}
