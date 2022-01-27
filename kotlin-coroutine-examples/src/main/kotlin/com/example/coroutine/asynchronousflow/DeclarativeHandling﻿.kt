package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    simple9()
        .onCompletion { log("Done") }
        .collect { value -> log(value) }
}
