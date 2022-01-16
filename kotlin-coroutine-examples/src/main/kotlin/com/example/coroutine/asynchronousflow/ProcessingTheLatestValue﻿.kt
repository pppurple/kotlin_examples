package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        simple5()
            .collectLatest { value -> // cancel & restart on the latest value
                log("Collecting $value")
                delay(300) // pretend we are processing it for 300 ms
                log("Done $value")
            }
    }
    log("Collected in $time ms")
}
