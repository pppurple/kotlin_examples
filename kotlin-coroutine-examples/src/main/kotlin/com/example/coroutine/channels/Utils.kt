package com.example.coroutine.channels

import java.time.Instant

fun <T> log(msg: T) = println("$msg [${Instant.now()}] [${Thread.currentThread().name}]")
