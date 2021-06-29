package com.example.coroutine.coroutinecontextanddispatchers

import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("My job is ${coroutineContext[Job]}")
    println(coroutineContext[Job]?.isActive)
}
