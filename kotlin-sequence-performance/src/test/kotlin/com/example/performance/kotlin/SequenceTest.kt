package com.example.performance.kotlin

import com.example.performance.kotlin.model.getRandomPersonSequence
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

class SequenceTest {
    @Test
    fun hugeSequence() {
        val persons = getRandomPersonSequence(2_000_000)

        val time = measureTimeMillis {
            val subList = persons
                .filter { it.age == 20 }
                .map { it.copy(country = "America") }
                .take(10)
            subList.forEach { println(it) }
        }

        println("time: $time")
    }
}
