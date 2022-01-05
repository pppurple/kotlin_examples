package com.example.coroutine.asynchronousflow

import java.time.Instant

fun simpleSeq(): Sequence<Int> = sequence { // sequence builder
    for (i in 1..3) {
        Thread.sleep(500) // pretend we are computing it
        yield(i) // yield next value
    }
}

fun main() {
    simpleSeq().forEach { value -> println("$value [${Instant.now()}] [${Thread.currentThread().name}]") }
}
