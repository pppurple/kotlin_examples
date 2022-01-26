package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

// Imitate a flow of events
fun events(): Flow<Int> = (1..3).asFlow().onEach { delay(100) }

/*
fun main() = runBlocking {
    events()
        .onEach { event -> log("Event: $event") }
        .collect() // <--- Collecting the flow waits
    log("Done")
}
*/

fun main() = runBlocking {
    events()
        .onEach { event -> log("Event: $event") }
        .launchIn(this) // <--- Launching the flow in a separate coroutine
    log("Done")
}
