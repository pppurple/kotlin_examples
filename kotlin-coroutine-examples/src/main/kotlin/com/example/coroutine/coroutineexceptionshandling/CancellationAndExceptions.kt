package com.example.coroutine.coroutineexceptionshandling

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

fun main() = runBlocking {
    val job = launch {
        val child = launch {
            try {
                delay(Long.MAX_VALUE)
            } finally {
                log("Child is cancelled")
            }
        }
        yield()
        log("Cancelling child")
        child.cancel()
        child.join()
        yield()
        log("Parent is not cancelled")
    }
    job.join()
}
