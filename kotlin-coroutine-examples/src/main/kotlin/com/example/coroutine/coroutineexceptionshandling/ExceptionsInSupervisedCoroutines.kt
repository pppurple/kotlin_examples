package com.example.coroutine.coroutineexceptionshandling

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        log("CoroutineExceptionHandler got $exception")
    }
    supervisorScope {
        val child = launch(handler) {
            log("The child throws an exception")
            throw AssertionError()
        }
        log("The scope is completing")
    }
    log("The scope is completed")
}
