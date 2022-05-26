package com.example.jmx

import java.lang.management.ManagementFactory
import javax.management.MBeanServer
import javax.management.ObjectName

fun main() {
    val mbs: MBeanServer = ManagementFactory.getPlatformMBeanServer()

    val name = ObjectName("com.example.jmx:type=Hello")

    val mbean = Hello()

    mbs.registerMBean(mbean, name)

    println("Waiting forever...")
    Thread.sleep(Long.MAX_VALUE)
}
