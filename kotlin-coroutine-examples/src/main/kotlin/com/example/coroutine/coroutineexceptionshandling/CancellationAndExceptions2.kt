package com.example.coroutine.coroutineexceptionshandling

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@OptIn(DelicateCoroutinesApi::class)
fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        log("CoroutineExceptionHandler got $exception")
    }
    val job = GlobalScope.launch(handler) {
        launch { // the first child
            try {
                delay(Long.MAX_VALUE)
            } finally {
                withContext(NonCancellable) {
                    log("Children are cancelled, but exception is not handled until all children terminate")
                    delay(100)
                    log("The first child finished its non cancellable block")
                }
            }
        }
        launch { // the second child
            delay(10)
            log("Second child throws an exception")
            throw ArithmeticException()
        }
    }
    job.join()
}
