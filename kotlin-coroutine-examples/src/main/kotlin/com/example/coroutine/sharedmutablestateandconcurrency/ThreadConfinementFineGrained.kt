package com.example.coroutine.sharedmutablestateandconcurrency

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

val counterContext = newSingleThreadContext("CounterContext")
var fineGrainedCounter = 0

fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        massiveRun {
            // confine each increment to a single-threaded context
            withContext(counterContext) {
                fineGrainedCounter++
            }
        }
    }
    log("Counter = $fineGrainedCounter")
}
