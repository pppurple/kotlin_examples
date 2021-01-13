package com.example.performance.kotlin.model

import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils

data class Person(
    val name: String,
    val age: Int,
    val country: String
)

fun getRandomPersonList(num: Long): List<Person> {
    return (1..num).map {
        Person(
            name = RandomStringUtils.randomAlphanumeric(10),
            age = RandomUtils.nextInt(1, 80),
            country = RandomStringUtils.randomAlphanumeric(5)
        )
    }.toList()
}

fun getRandomPersonSequence(num: Long): Sequence<Person> {
    return (1..num).map {
        Person(
            name = RandomStringUtils.randomAlphanumeric(10),
            age = RandomUtils.nextInt(1, 80),
            country = RandomStringUtils.randomAlphanumeric(5)
        )
    }.asSequence()
}
