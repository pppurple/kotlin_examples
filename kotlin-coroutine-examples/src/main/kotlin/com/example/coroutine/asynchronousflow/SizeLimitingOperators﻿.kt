package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking

fun numbers(): Flow<Int> = flow {
    try {
        emit(1)
        emit(2)
        log("This line will not execute.")
        emit(3)
    } finally {
        log("Finally in numbers.")
    }
}

fun main() = runBlocking {
    numbers()
        .take(2) // take only the first two
        .collect { response ->
            log(response)
        }
}
