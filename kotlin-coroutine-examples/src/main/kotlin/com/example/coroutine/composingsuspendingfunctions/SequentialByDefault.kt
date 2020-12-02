package com.example.coroutine.composingsuspendingfunctions

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.time.Instant
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        val one = doSomethingUsefulOne()
        val two = doSomethingUsefulTwo()
        println("The answer is ${one + two}. [${Instant.now()}] [${Thread.currentThread().name}]")
    }
    println("Completed in $time ms. [${Instant.now()}] [${Thread.currentThread().name}]")
}

suspend fun doSomethingUsefulOne(): Int {
    println("processing task one... [${Instant.now()}] [${Thread.currentThread().name}]")
    delay(1_000L)
    println("done task one. [${Instant.now()}] [${Thread.currentThread().name}]")
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    println("processing task two... [${Instant.now()}] [${Thread.currentThread().name}]")
    delay(1_000L)
    println("done task two. [${Instant.now()}] [${Thread.currentThread().name}]")
    return 29
}
