package com.example.kotlin

import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

fun main(args: Array<String>) {
    run {
        // Using kotlin reflection
        // mutable data class
        val mutable = MutablePerson(
            "Anna",
            20
        )
        println("before: $mutable")

        val ageProperty = mutable::class.memberProperties
            .first { it.name == "age" } as KMutableProperty<*>
        ageProperty.isAccessible = true
        ageProperty.setter.call(mutable, 21)

        println("after : $mutable")
    }

    run {
        // Using kotlin reflection
        // immutable data class

        // error!!
        /*
        val readOnly = RealOnlyPerson(
            "Anna",
            20
        )
        println("before: $readOnly")

        val ageProperty = readOnly::class.memberProperties
            .first { it.name == "age" } as KMutableProperty<*>
        ageProperty.isAccessible = true
        ageProperty.setter.call(readOnly, 21)

        println("after : $readOnly")
        */
    }

    run {
        // Using Java reflection
        // mutable data class
        val mutable = MutablePerson(
            "Bob",
            30
        )
        println("before: $mutable")

        val setter = mutable::class.java.getMethod("setAge", Int::class.java)
        // val setter = mutable.javaClass.getMethod("setAge", Int::class.java)
        setter.invoke(mutable, 33)

        println("after : $mutable")
    }

    run {
        // Using Java reflection
        // immutable data class

        // error!!
        /*
        val readOnly = RealOnlyPerson(
            "Bob",
            30
        )
        println("before: $readOnly")

        val setter = readOnly::class.java.getMethod("setAge", Int::class.java)
        // val setter = mutable.javaClass.getMethod("setAge", Int::class.java)
        setter.invoke(readOnly, 33)

        println("after : $readOnly")
        */
    }

    run {
        // Using Java reflection
        // mutable data class
        val mutable = MutablePerson(
            "Cindy",
            40
        )
        println("before: $mutable")

        val age = mutable::class.java.getDeclaredField("age")
        age.isAccessible = true
        age.set(mutable, 44)

        println("after : $mutable")
    }

    run {
        // Using Java reflection
        // immutable data class
        val readOnly = RealOnlyPerson(
            "Cindy",
            40
        )
        println("before: $readOnly")

        val age = readOnly::class.java.getDeclaredField("age")
        age.isAccessible = true
        age.set(readOnly, 44)

        println("after : $readOnly")
    }
}

data class MutablePerson(
    var name: String,
    var age: Int
)

data class RealOnlyPerson(
    val name: String,
    val age: Int
)
