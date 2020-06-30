package com.example.coroutin.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    GlobalScope.launch {
        delay(1_000L)
        println("World!")
    }
    println("Hello,")
    runBlocking {
        delay(2_000L)
    }
}