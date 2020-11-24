package com.example.coroutin.basic

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        delay(200L)
        println("Task from runBlocking [${Thread.currentThread().name}]")
    }

    coroutineScope {
        launch {
            delay(500L)
            println("Task from nested launch [${Thread.currentThread().name}]")
        }

        delay(100L)
        println("Task from coroutine scope [${Thread.currentThread().name}]")
    }

    println("Coroutine scope is over [${Thread.currentThread().name}]")
}
