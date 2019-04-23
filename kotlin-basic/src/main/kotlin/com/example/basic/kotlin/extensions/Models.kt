package com.example.basic.kotlin.extensions

/*
class Group(var name: String, var member: List<Member>) {
    constructor(): this("", listOf<Member>())
}
*/
class Item(val id: Long, val name: String)

open class Person(var name: String, var age: Int)

class Member(name: String, age: Int, var nickName: String): Person(name, age)
