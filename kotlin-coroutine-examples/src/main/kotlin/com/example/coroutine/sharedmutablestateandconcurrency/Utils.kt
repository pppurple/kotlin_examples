package com.example.coroutine.sharedmutablestateandconcurrency

import java.time.Instant

fun log(msg: String) = println("$msg [${Instant.now()}] [${Thread.currentThread().name}]")
