package com.example.coroutine.coroutineexceptionshandling

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.yield

fun main() = runBlocking {
    try {
        supervisorScope {
            val child = launch {
                try {
                    log("The child is sleeping")
                    delay(Long.MAX_VALUE)
                } finally {
                    log("The child is cancelled")
                }
            }
            // Give our child a chance to execute and print using yield
            yield()
            log("Throwing an exception from the scope")
            throw AssertionError()
        }
    } catch (e: AssertionError) {
        log("Caught an assertion error")
    }
}
