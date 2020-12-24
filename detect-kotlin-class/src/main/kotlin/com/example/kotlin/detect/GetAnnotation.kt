package com.example.kotlin.detect

import com.example.kotlin.model.KotlinModel
import com.exmple.kotlin.model.JavaModel

fun main() {
    val kotlinModel = KotlinModel()
    val javaModel = JavaModel()

    println("kotlinModel KClass annotations")
    kotlinModel::class.annotations
        .forEach { println(it) }

    println("kotlinModel Class annotations")
    kotlinModel::class.java.annotations
        .forEach { println(it) }

    println("kotlinModel Class declaredAnnotations")
    kotlinModel::class.java.declaredAnnotations
        .forEach { println(it) }

    println("javaModel KClass annotations")
    javaModel::class.annotations
        .forEach { println(it) }

    println("javaModel Class annotations")
    javaModel::class.java.annotations
        .forEach { println(it) }

    println("javaModel Class declaredAnnotations")
    javaModel::class.java.declaredAnnotations
        .forEach { println(it) }
}
