package com.example.hbase

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.TableName
import org.apache.hadoop.hbase.client.*
import org.apache.hadoop.hbase.util.Bytes

fun main(args: Array<String>) {
    val conf= HBaseConfiguration.create()
    conf.set("hbase.zookeeper.quorum", "127.0.0.1")
    //conf.set("hbase.zookeeper.quorum", "localhost")
    conf.set("hbase.zookeeper.property.clientPort","2181")
    //conf.set("hbase.security.authentication", "kerberos")
    //val table: HTableInterface = HTable(conf, "")

    ConnectionFactory.createConnection(conf).use {
        val tableName= TableName.valueOf("myspace:people")
        it.getTable(tableName).use { table ->
            cleanup(table) // cleanup

            // put
            println("******put")
            put(table)

            // exists
            println("******exists")
            exists(table)

            // get
            println("******get")
            get(table)

            // delete (row)
            println("******delete (row)")
            cleanup(table) // cleanup
            deleteRow(table)

            // delete (column)
            println("******delete (column)")
            cleanup(table) // cleanup
            deleteColumn(table)

            // scan
            println("******scan")
            cleanup(table) // cleanup
            scan(table)

            // mutateRow
            println("******mutateRow")
            cleanup(table) // cleanup
            mutateRow(table)
        }
    }
}

fun put(table: Table) {
    val put = Put(Bytes.toBytes("row1"))
    // row1.add(Bytes.toBytes(""), Bytes.toBytes(""), Bytes.toBytes(""))
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("Alice"))
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes("20"))
    table.put(put)
}

fun exists(table: Table) {
    val getRow = Get(Bytes.toBytes("row1"))
    val existsRow = table.exists(getRow)
    println("row1 exists?: $existsRow")
    getRow.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"))
    val existsCol = table.exists(getRow)
    println("row1 f name exists?: $existsCol")
}

fun get(table: Table) {
    val get = Get(Bytes.toBytes("row1"))
    get.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"))
    get.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"))
    val getResult = table.get(get)
    val name = Bytes.toString(getResult.getValue(Bytes.toBytes("f"), Bytes.toBytes("name")))
    val age = Bytes.toString(getResult.getValue(Bytes.toBytes("f"), Bytes.toBytes("age")))
    println("row1 name: $name")
    println("row1 age: $age")
}

fun deleteRow(table: Table) {
    // prepare
    val put = Put(Bytes.toBytes("row1"))
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("Alice"))
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes("20"))
    table.put(put)

    // delete (row)
    val deleteRow = Delete(Bytes.toBytes("row1"))
    table.delete(deleteRow)
}

fun deleteColumn(table: Table) {
    // prepare
    val put = Put(Bytes.toBytes("row1"))
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("Alice"))
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes("20"))
    table.put(put)

    // delete (column)
    val delete = Delete(Bytes.toBytes("row1"))
    delete.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"))
    table.delete(delete)
}

fun scan(table: Table) {
    // prepare
    val put = Put(Bytes.toBytes("row1"))
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("Alice"))
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes("20"))
    table.put(put)
    val put2 = Put(Bytes.toBytes("row2"))
    put2.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("Bobby"))
    put2.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes("33"))
    table.put(put2)
    val put3 = Put(Bytes.toBytes("row3"))
    put3.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("Cindy"))
    put3.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes("19"))
    table.put(put3)

    // scan
    val scan = Scan()
    val resultScanner = table.getScanner(scan)
    resultScanner.forEach { result ->
        val scanName = Bytes.toString(result.getValue(Bytes.toBytes("f"), Bytes.toBytes("name")))
        val scanAge = Bytes.toString(result.getValue(Bytes.toBytes("f"), Bytes.toBytes("age")))
        println("scan name: $scanName")
        println("scan age: $scanAge")
    }
}

fun mutateRow(table: Table) {
    val put = Put(Bytes.toBytes("row1"))
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("Alice"))
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes("20"))
    val delete = Delete(Bytes.toBytes("row1"))
    delete.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"))

    // mutateRow
    val rowMutations = RowMutations(Bytes.toBytes("row1"))
    rowMutations.add(put)
    rowMutations.add(delete)
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("country"), Bytes.toBytes("Japan"))
    rowMutations.add(put)
    table.mutateRow(rowMutations)

    // scan
    val scan = Scan()
    val resultMutateRow = table.getScanner(scan)
    resultMutateRow.forEach { result ->
        val scanName = Bytes.toString(result.getValue(Bytes.toBytes("f"), Bytes.toBytes("name")))
        val scanAge = Bytes.toString(result.getValue(Bytes.toBytes("f"), Bytes.toBytes("age")))
        val scanCountry = Bytes.toString(result.getValue(Bytes.toBytes("f"), Bytes.toBytes("country")))
        println("scan name: $scanName")
        println("scan age: $scanAge")
        println("scan country: $scanCountry")
    }
}

fun cleanup(table: Table) {
    val deleteAll = listOf(
            Delete(Bytes.toBytes("row1")),
            Delete(Bytes.toBytes("row2")),
            Delete(Bytes.toBytes("row3"))
    )
    deleteAll.forEach {
        table.delete(it)
    }
}
