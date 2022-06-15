package com.example.jmx

interface HelloMBean {
    val name: String
    var cacheSize: Int
    fun sayHello()
    fun add(x: Int, y: Int): Int
}
