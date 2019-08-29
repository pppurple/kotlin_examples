package com.example.coroutin.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    GlobalScope.launch {
        delay(1_000L)
        println("World!")
    }
    println("Hello,")
    delay(2_000L)
}
