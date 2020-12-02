package com.example.coroutine.composingsuspendingfunctions

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.time.Instant
import kotlin.system.measureTimeMillis

fun main() = runBlocking<Unit> {
    try {
        failConcurrentSum()
    } catch (e: java.lang.ArithmeticException) {
        println("Computation failed with ArithmeticException. [${Instant.now()}] [${Thread.currentThread().name}]")
    }
}

suspend fun failConcurrentSum(): Int = coroutineScope {
    val one = async<Int> {
        try {
            delay(Long.MAX_VALUE)
            42
        } finally {
            println("First child was cancelled. [${Instant.now()}] [${Thread.currentThread().name}]")
        }
    }
    val two = async<Int> {
        println("Second child throws an exception. [${Instant.now()}] [${Thread.currentThread().name}]")
        throw ArithmeticException()
    }
    one.await() + two.await()
}
