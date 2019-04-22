package com.example.basic.kotlin.extensions

import org.jeasy.random.EasyRandom

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        // no args
        val amount = 100
        println(amount.triple())
        println(100.triple())

        // with args
        val hello = "Hello"
        println(hello.decorate("##"))
        println("Hello".decorate("$$"))

        // doubledHashCode
        val member = Member("Anny", 20, "an")
        println(member.hashCode())
        println(member.doubledHashCode())

        // random
        val person = Person("bob", 32)
        person.printHashCode()

        var group2 = EasyRandom().nextObject(Group::class.java)
        println(group2.name)
        println(group2.member)

        // extension property
        // abs
        println(10.abs)
        println(-10.abs)
    }

    fun Int.triple() = this * 3

    fun String.decorate(str: String) = str + this + str

    fun Any.doubledHashCode(): Long = (this.hashCode() * 2).toLong()

    fun <T> T.printHashCode() = println(this.hashCode())

    inline fun <reified T> T.random(): T = EasyRandom().nextObject(T::class.java)

    val Int.abs
        get() = if (this >= 0) this else -1 * this

    val <T> List<T>.showWithComma: String
        get() = map { it.toString() + "," }
}
