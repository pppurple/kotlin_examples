package com.example.jmx

class Hello : HelloMBean {
    companion object {
        private const val DEFAULT_CACHE_SIZE = 200
    }
    override val name = "Reginald"
    override var cacheSize = DEFAULT_CACHE_SIZE

    override fun sayHello() {
        println("hello, world")
    }

    override fun add(x: Int, y: Int) = x + y
}
