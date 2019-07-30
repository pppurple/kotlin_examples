package com.example.kotlin.mybatis.model

import com.example.kotlin.mybatis.annotations.NoArg

data class Group (
    val groupId: Int,
    val name: String
)

@NoArg
data class GroupUser (
    val groupId: Int,
    val users: List<User>
)

/*
 you will get error if you use data class without noarg plugin
 */
/*
class GroupUser {
    var groupId: Int = 0
    var users = mutableListOf<User>()
}
*/
