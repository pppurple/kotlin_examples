package com.example.kotlin.detect

import com.example.kotlin.model.KotlinModel
import com.exmple.kotlin.model.JavaModel
import kotlin.reflect.KClass

fun main() {
    val kotlinModel = KotlinModel()
    val javaModel = JavaModel()

    println("kotlinModel: ${kotlinModel::class.java.isKotlinClass()}")
    println("javaModel: ${javaModel::class.java.isKotlinClass()}")

    // Can't find from KClass.annotation
    // println("kotlinModel: ${kotlinModel::class.isKotlinClass()}")
    // println("javaModel: ${javaModel::class.isKotlinClass()}")
}

fun Class<*>.isKotlinClass(): Boolean {
    return this.getDeclaredAnnotation(Metadata::class.java) != null
}

fun KClass<*>.isKotlinClass(): Boolean {
    return this.annotations.any {
        it.annotationClass == Metadata::class
    }
}
