package com.example.coroutine.sharedmutablestateandconcurrency

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@Volatile
var volatileCounter = 0

fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        massiveRun {
            volatileCounter++
        }
    }
    log("Counter = $volatileCounter")
}
