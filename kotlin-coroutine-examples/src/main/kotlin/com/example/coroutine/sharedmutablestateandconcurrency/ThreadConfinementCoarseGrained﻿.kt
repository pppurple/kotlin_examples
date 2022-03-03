package com.example.coroutine.sharedmutablestateandconcurrency

import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

val counterContext2 = newSingleThreadContext("CounterContext")
var coarseGrainedCounter = 0

fun main() = runBlocking {
    // confine everything to a single-threaded context
    withContext(counterContext2) {
        massiveRun {
            coarseGrainedCounter++
        }
    }
    log("Counter = $coarseGrainedCounter")
}
