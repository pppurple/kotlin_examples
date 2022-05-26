package com.example.jmx

interface HelloMBean {
    val cacheSize: Int
    fun sayHello()
    fun add(x: Int, y: Int): Int
    fun getName(): String
}
