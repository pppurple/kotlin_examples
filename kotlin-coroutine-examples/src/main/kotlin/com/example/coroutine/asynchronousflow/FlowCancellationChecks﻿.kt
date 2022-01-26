package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun foo(): Flow<Int> = flow {
    for (i in 1..5) {
        log("Emitting $i")
        emit(i)
    }
}

fun main() = runBlocking {
    foo().collect { value ->
        if (value == 3) cancel()
        log(value)
    }
}

/*
fun main() = runBlocking {
    (1..5).asFlow().collect { value ->
        if (value == 3) cancel()
        log(value)
    }
}
*/
