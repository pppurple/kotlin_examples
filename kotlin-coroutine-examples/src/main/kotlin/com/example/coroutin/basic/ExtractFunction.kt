package com.example.coroutin.basic

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch { doWorld() }
    println("Hello, [${Thread.currentThread().name}]")
}

suspend fun doWorld() {
    delay(1_000L)
    println("World! [${Thread.currentThread().name}]")
}
