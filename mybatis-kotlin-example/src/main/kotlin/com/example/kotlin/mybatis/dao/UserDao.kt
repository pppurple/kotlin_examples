package com.example.kotlin.mybatis.dao

import com.example.kotlin.mybatis.model.GroupUser
import com.example.kotlin.mybatis.model.User
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Select

interface UserDao {
    @Select(
        "SELECT * FROM user",
        "WHERE user_id = #{userId}"
    )
    fun selectByUserId(userId: Int): User

    @Select(
        "SELECT * FROM user"
    )
    fun selectAll(): List<User>

    @Select(
        "SELECT gu.group_id, u.* FROM group_user gu",
        "JOIN user u ON gu.user_id = u.user_id",
        "WHERE gu.group_id = #{groupId}"
    )
    @ResultMap("groupUser")
    fun selectGroupUser(groupId: Int): GroupUser

    // this is not allowed
    /*
    @Select(
        "SELECT * FROM user",
        "ORDER BY ${column}"
    )
    */
    @Select(
        "SELECT * FROM user",
        "ORDER BY ${"$"}{column}"
    )
    fun selectOrderBy(column: String): List<User>
}
