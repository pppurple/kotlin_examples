package com.example.coroutine.channels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun CoroutineScope.produceNumbers2() = produce<Int> {
    var x = 1 // start from 1
    while (true) {
        send(x++) // produce next
        delay(100) // wait 0.1s
    }
}

fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
    for (msg in channel) {
        log("Processor #$id received $msg")
    }
}

fun main() = runBlocking {
    val producer = produceNumbers2()
    repeat(5) {
        launchProcessor(it, producer)
    }
    delay(950)
    producer.cancel() // cancel producer coroutine and thus kill them all
}
