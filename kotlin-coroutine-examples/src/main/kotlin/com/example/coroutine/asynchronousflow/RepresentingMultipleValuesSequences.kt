package com.example.coroutine.asynchronousflow

import java.time.Instant

fun simpleSeq(): Sequence<Int> = sequence {
    for (i in 1..3) {
        Thread.sleep(500)
        yield(i)
    }
}

fun main() {
    simpleSeq().forEach { value -> println("[${Instant.now()}] $value") }
}
