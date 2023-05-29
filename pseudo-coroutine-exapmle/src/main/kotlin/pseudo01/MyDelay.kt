package pseudo01

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.startCoroutine
import kotlin.coroutines.suspendCoroutine
import util.log

fun main() {
    myDelayHelloWorld()
}

fun myDelayHelloWorld() {
    val suspendLambda: suspend () -> Unit = {
        log("Hello")
        myDelay(2000L)
        log("World!")
    }
    suspendLambda.startCoroutine(MyContinuation())
}

suspend fun myDelay(timeMillis: Long) {
    return suspendCoroutine { continuation ->
        Thread.sleep(timeMillis)
        continuation.resume(Unit)
    }
}

class MyContinuation<T> : Continuation<T> {
    override val context: CoroutineContext = EmptyCoroutineContext
    override fun resumeWith(result: Result<T>) {
        // do nothing
    }
}
