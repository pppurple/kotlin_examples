package com.example.coroutine.cancellationandtimeout

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

fun main() = runBlocking {
    repeat(100_000) {
        launch {
            var resource: Resource? = null
            try {
                withTimeout(60) {
                    delay(50)
                    resource = Resource()
                }
            } finally {
                resource?.close()
            }
        }
    }
    // always print zero
    println(acquired)
}

