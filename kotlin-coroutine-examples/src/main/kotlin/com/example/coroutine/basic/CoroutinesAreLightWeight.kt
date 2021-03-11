package com.example.coroutine.basic

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    repeat(100_000) {
        launch {
            delay(5_000L)
            print(".")
        }
    }
}
