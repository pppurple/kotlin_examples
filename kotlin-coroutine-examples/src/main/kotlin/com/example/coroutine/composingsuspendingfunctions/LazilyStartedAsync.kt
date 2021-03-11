package com.example.coroutine.composingsuspendingfunctions

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.time.Instant
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
        one.start()
        two.start()
        println("The answer is ${one.await() + two.await()}. [${Instant.now()}] [${Thread.currentThread().name}]")
    }
    println("Completed in $time ms. [${Instant.now()}] [${Thread.currentThread().name}]")
}

// Don't call start().
// This will lead to sequential behavior
/*
fun main() = runBlocking {
    val time = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
        println("The answer is ${one.await() + two.await()}. [${Instant.now()}] [${Thread.currentThread().name}]")
    }
    println("Completed in $time ms. [${Instant.now()}] [${Thread.currentThread().name}]")
}
*/
