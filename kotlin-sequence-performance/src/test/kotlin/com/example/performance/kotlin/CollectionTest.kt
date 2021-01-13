package com.example.performance.kotlin

import com.example.performance.kotlin.model.getRandomPersonList
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

class CollectionTest {
    @Test
    fun hugeList() {
        val persons = getRandomPersonList(2_000_000)

        val time = measureTimeMillis {
            val subList = persons
                .filter { it.age == 20 }
                .map { it.copy(country = "America") }
                .take(10)
            subList.forEach { println(it) }
        }

        println("time: $time")
    }

    @Test
    fun hugeListAsSequence() {
        val persons = getRandomPersonList(2_000_000)

        val time = measureTimeMillis {
            val subList = persons
                .asSequence()
                .filter { it.age == 20 }
                .map { it.copy(country = "America") }
                .take(10)
            subList.forEach { println(it) }
        }

        println("time: $time")
    }
}
