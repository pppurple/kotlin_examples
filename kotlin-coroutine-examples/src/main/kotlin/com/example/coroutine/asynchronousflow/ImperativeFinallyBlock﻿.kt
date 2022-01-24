package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.runBlocking

fun simple9(): Flow<Int> = (1..3).asFlow()

fun main() = runBlocking {
    try {
        simple9().collect { value -> log(value) }
    } finally {
        log("Done")
    }
}
