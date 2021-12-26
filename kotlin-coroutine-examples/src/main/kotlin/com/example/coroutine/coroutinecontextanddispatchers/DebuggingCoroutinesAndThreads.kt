package com.example.coroutine.coroutinecontextanddispatchers

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun log(msg: String) = println("$msg [${Instant.now()}] [${Thread.currentThread().name}]")

fun main() = runBlocking {
    val a = async {
        log("I'm computing a piece of the answer")
        6
    }
    val b = async {
        log("I'm computing another piece of the answer")
        7
    }
    log("The answer is ${a.await() * b.await()}")
}
