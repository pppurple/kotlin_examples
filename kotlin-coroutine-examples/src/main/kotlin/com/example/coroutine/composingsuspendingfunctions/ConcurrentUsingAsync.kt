package com.example.coroutine.composingsuspendingfunctions

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.time.Instant
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        println("The answer is ${one.await() + two.await()}. [${Instant.now()}] [${Thread.currentThread().name}]")
    }
    println("Completed in $time ms. [${Instant.now()}] [${Thread.currentThread().name}]")
}
