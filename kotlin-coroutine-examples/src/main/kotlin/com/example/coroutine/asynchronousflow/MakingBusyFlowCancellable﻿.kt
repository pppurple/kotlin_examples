package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    (1..5).asFlow().cancellable()
        .collect { value ->
            if (value == 3) cancel()
            log(value)
        }
}
