package com.example.kotlin.mybatis.model

data class User (
    val userId: Int,
    val name: String,
    val age: Int
)

/*
 you will get error if you use data class without noarg plugin
 */
/*
class User {
    var userId: Int = 0
    lateinit var name: String
    var age: Int = 0
}
*/
