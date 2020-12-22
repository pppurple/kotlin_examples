package com.example.coroutine.coroutinecontextanddispatchers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asContextElement
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import java.time.Instant

val threadLocal = ThreadLocal<String?>()

fun main() = runBlocking {
    threadLocal.set("main")
    println("[${Instant.now()}] Pre-main, current thread: [${Thread.currentThread()}]," + " thread local value: '${threadLocal.get()}'")
    val job = launch(Dispatchers.Default + threadLocal.asContextElement(value = "launch")) {
        println("[${Instant.now()}] Launch start, current thread: [${Thread.currentThread()}]," + " thread local value: '${threadLocal.get()}'")
        yield()
        println("[${Instant.now()}] After yield, current thread: [${Thread.currentThread()}]," + " thread local value: '${threadLocal.get()}'")
    }
    job.join()
    println("[${Instant.now()}] Post-main, current thread: [${Thread.currentThread()}]," + " thread local value: '${threadLocal.get()}'")
}
