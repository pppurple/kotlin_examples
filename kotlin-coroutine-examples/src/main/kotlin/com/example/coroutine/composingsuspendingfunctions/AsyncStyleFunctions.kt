package com.example.coroutine.composingsuspendingfunctions

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import java.time.Instant
import kotlin.system.measureTimeMillis

fun main() {
    val time = measureTimeMillis {
        val one = somethingUsefulOneAsync()
        val two = somethingUsefulTwoAsync()
        runBlocking {
            // doErrorTask()
            println("The answer is ${one.await() + two.await()}. [${Instant.now()}] [${Thread.currentThread().name}]")
        }
    }
    println("Completed in $time ms. [${Instant.now()}] [${Thread.currentThread().name}]")
}

fun somethingUsefulOneAsync() = GlobalScope.async {
    doSomethingUsefulOne()
}

fun somethingUsefulTwoAsync() = GlobalScope.async {
    doSomethingUsefulTwo()
}

fun doErrorTask() {
    Thread.sleep(10)
    throw UnexpectedException()
}

class UnexpectedException : Exception()
