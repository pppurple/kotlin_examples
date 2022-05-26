package com.example.jmx

class Hello : HelloMBean {
    private val name = "Reginald"
    private val DEFAULT_CACHE_SIZE = 200
    override val cacheSize = DEFAULT_CACHE_SIZE

    override fun sayHello() {
        println("hello, world")
    }

    override fun add(x: Int, y: Int) = x + y

    override fun getName(): String = name
}
