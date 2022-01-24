package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    simple8()
        .onEach { value ->
            check(value <= 1) { "Collected $value" }
            log(value)
        }
        .catch { e -> log("Caught $e") }
        .collect()
}
