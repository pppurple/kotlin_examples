package com.example.coroutine.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = GlobalScope.launch {
        delay(1_000L)
        println("World! [${Thread.currentThread().name}]")
    }
    println("Hello, [${Thread.currentThread().name}]")
    job.join()
}
