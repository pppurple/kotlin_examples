package com.example.coroutine.sharedmutablestateandconcurrency

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger

val atomicCounter = AtomicInteger()

fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        massiveRun {
            atomicCounter.incrementAndGet()
        }
    }
    log("Counter = $atomicCounter")
}
