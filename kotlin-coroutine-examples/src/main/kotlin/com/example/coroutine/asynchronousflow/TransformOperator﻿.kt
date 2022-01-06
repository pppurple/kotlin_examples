package com.example.coroutine.asynchronousflow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main() = runBlocking {
    (1..3).asFlow() // a flow of requests
        .transform { request ->
            emit("Making request $request")
            emit(performRequest(request))
        }
        .collect { response ->
            println("$response [${Instant.now()}] [${Thread.currentThread().name}]")
        }
}
