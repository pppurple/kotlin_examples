package com.example.coroutin.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    GlobalScope.launch {
        delay(1_000L)
        println("World! [${Thread.currentThread().name}]")
    }
    println("Hello, [${Thread.currentThread().name}]")
    runBlocking {
        println("...delay..., [${Thread.currentThread().name}]")
        delay(2_000L)
    }
}

