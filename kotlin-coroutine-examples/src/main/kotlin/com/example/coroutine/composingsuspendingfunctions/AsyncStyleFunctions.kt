package com.example.coroutine.composingsuspendingfunctions

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import java.time.Instant
import kotlin.system.measureTimeMillis

// note that we don't have `runBlocking` to the right of `main` in this example
fun main() {
    val time = measureTimeMillis {
        // we can initiate async actions outside of a coroutine
        val one = somethingUsefulOneAsync()
        val two = somethingUsefulTwoAsync()
        // but waiting for a result must involve either suspending or blocking.
        // here we use `runBlocking { ... }` to block the main thread while waiting for the result
        runBlocking {
            // doErrorTask()
            println("The answer is ${one.await() + two.await()}. [${Instant.now()}] [${Thread.currentThread().name}]")
        }
    }
    println("Completed in $time ms. [${Instant.now()}] [${Thread.currentThread().name}]")
}

// The result type of somethingUsefulOneAsync is Deferred<Int>
fun somethingUsefulOneAsync() = GlobalScope.async {
    doSomethingUsefulOne()
}

// The result type of somethingUsefulTwoAsync is Deferred<Int>
fun somethingUsefulTwoAsync() = GlobalScope.async {
    doSomethingUsefulTwo()
}

fun doErrorTask() {
    Thread.sleep(10)
    throw UnexpectedException()
}

class UnexpectedException : Exception()
