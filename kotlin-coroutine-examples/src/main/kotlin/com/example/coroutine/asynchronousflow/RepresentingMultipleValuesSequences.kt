package com.example.coroutine.asynchronousflow

fun simpleSeq(): Sequence<Int> = sequence { // sequence builder
    for (i in 1..3) {
        Thread.sleep(500) // pretend we are computing it
        yield(i) // yield next value
    }
}

fun main() {
    simpleSeq().forEach { value -> log(value) }
}
