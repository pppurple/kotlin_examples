package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    simple7()
        .catch { e -> emit("Caught $e") } // emit on exception
        .collect { value -> log(value) }
}
