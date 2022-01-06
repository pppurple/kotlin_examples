package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun numbers(): Flow<Int> = flow {
    try {
        emit(1)
        emit(2)
        println("This line will not execute. [${Instant.now()}] [${Thread.currentThread().name}]")
        emit(3)
    } finally {
        println("Finally in numbers. [${Instant.now()}] [${Thread.currentThread().name}]")
    }
}

fun main() = runBlocking {
    numbers()
        .take(2) // take only the first two
        .collect { response ->
            println("$response [${Instant.now()}] [${Thread.currentThread().name}]")
        }
}
