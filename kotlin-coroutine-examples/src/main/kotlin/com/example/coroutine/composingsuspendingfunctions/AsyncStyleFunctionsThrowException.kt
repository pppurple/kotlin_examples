package com.example.coroutine.composingsuspendingfunctions

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.time.Instant
import kotlin.system.measureTimeMillis

// note that we don't have `runBlocking` to the right of `main` in this example
fun main() {
    val time = measureTimeMillis {
        // we can initiate async actions outside of a coroutine
        val one = usefulOneAsync()
        val two = usefulTwoAsync()
        // but waiting for a result must involve either suspending or blocking.
        // here we use `runBlocking { ... }` to block the main thread while waiting for the result
        runBlocking {
            println("The answer is ${one.await() + two.await()}. [${Instant.now()}] [${Thread.currentThread().name}]")
        }
    }
    println("Completed in $time ms. [${Instant.now()}] [${Thread.currentThread().name}]")
}

// The result type of somethingUsefulOneAsync is Deferred<Int>
fun usefulOneAsync() = GlobalScope.async<Int> {
    try {
        delay(Long.MAX_VALUE) // Emulates very long computation
        doSomethingUsefulOne()
    } finally {
        println("First child was cancelled. [${Instant.now()}] [${Thread.currentThread().name}]")
    }
}

// The result type of somethingUsefulTwoAsync is Deferred<Int>
fun usefulTwoAsync() = GlobalScope.async<Int> {
    println("Second child throws an exception. [${Instant.now()}] [${Thread.currentThread().name}]")
    throw ArithmeticException()
}
