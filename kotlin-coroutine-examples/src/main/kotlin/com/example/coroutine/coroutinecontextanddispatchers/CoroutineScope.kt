package com.example.coroutine.coroutinecontextanddispatchers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() = runBlocking {
    val activity = Activity()
    activity.doSomething()
    println("Launched coroutines [${Instant.now()}] [${Thread.currentThread().name}]")
    delay(500L)
    println("Destroying activity! [${Instant.now()}] [${Thread.currentThread().name}]")
    activity.destroy()
    delay(1000L)
}

class Activity {
    private val mainScope = CoroutineScope(Dispatchers.Default)

    fun destroy() {
        mainScope.cancel()
    }

    fun doSomething() {
        repeat(10) { i ->
            mainScope.launch {
                delay((i + 1) * 200L)
                println("Coroutine $i is done [${Instant.now()}] [${Thread.currentThread().name}]")
            }
        }
    }
}
