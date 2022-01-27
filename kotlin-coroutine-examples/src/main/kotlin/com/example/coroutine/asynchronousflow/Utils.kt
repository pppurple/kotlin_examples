package com.example.coroutine.asynchronousflow

import java.time.Instant

fun log(msg: String) = println("$msg [${Instant.now()}] [${Thread.currentThread().name}]")
