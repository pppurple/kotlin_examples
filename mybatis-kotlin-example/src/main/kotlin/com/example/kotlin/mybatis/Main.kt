package com.example.kotlin.mybatis

import com.example.kotlin.mybatis.dao.UserDao
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource
import org.apache.ibatis.mapping.Environment
import org.apache.ibatis.session.Configuration
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory

fun main(args: Array<String>) {
    val JDBC_URL = "jdbc:mysql://hostname:port/db_name"
    val JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"

    Class.forName(JDBC_DRIVER)

    val ds = UnpooledDataSource(JDBC_DRIVER, JDBC_URL, "username", "password")
    val environment = Environment("test", JdbcTransactionFactory(), ds)
    val config = Configuration(environment)
    config.isMapUnderscoreToCamelCase = true
    config.addMapper(UserDao::class.java)
    val sqlSessionFactory = SqlSessionFactoryBuilder().build(config)

    sqlSessionFactory.openSession().use { session ->
        // select one
        val userDao = session.getMapper(UserDao::class.java)
        val user = userDao.selectByUserId(1)
        println("-- select one --")
        println("user: $user")

        // select all
        println("-- select all --")
        val users = userDao.selectAll()
        users.forEach {
            println("user: $it")
        }

        // select GroupUser
        println("-- select using collection --")
        val groupUser = userDao.selectGroupUser(1)
        groupUser.users.forEach {
            println("user: $it")
        }

        // select order by
        println("-- select order by --")
        val sorted = userDao.selectOrderBy("name DESC")
        sorted.forEach {
            println("user: $it")
        }
    }
}
