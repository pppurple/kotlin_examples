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
    println("[${Instant.now()}] Launched coroutines [${Thread.currentThread().name}]")
    delay(500L)
    println("[${Instant.now()}] Destroying activity! [${Thread.currentThread().name}]")
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
                println("[${Instant.now()}] Coroutine $i is done [${Thread.currentThread().name}]")
            }
        }
    }
}
