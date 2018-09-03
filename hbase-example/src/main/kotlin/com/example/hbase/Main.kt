package com.example.hbase

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.TableName
import org.apache.hadoop.hbase.client.*
import org.apache.hadoop.hbase.filter.*
import org.apache.hadoop.hbase.util.Bytes

fun main(args: Array<String>) {
    val conf= HBaseConfiguration.create()
    conf.set("hbase.zookeeper.quorum", "127.0.0.1")
    conf.set("hbase.zookeeper.property.clientPort","2181")

    // deprecated!! (< 1.0)
    // val table: HTableInterface = HTable(conf, "")

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

            // filter
            println("******filter")
            cleanup(table) // cleanup
            filter(table)

            // mutateRow
            println("******mutateRow")
            cleanup(table) // cleanup
            mutateRow(table)

            // increment
            println("******increment")
            cleanup(table) // cleanup
            increment(table)

            // compare and swap
            println("******compare and swap")
            cleanup(table) // cleanup
            cas(table)
        }
    }
}

fun put(table: Table) {
    val put = Put(Bytes.toBytes("row1"))
    // row1.add(Bytes.toBytes(""), Bytes.toBytes(""), Bytes.toBytes(""))
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("Alice"))
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes(20))
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
    val age = Bytes.toInt(getResult.getValue(Bytes.toBytes("f"), Bytes.toBytes("age")))
    println("row1 name: $name")
    println("row1 age: $age")
}

fun deleteRow(table: Table) {
    // prepare
    val put = Put(Bytes.toBytes("row1"))
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("Alice"))
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes(20))
    table.put(put)

    // delete (row)
    val deleteRow = Delete(Bytes.toBytes("row1"))
    table.delete(deleteRow)
}

fun deleteColumn(table: Table) {
    // prepare
    val put = Put(Bytes.toBytes("row1"))
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("Alice"))
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes(20))
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
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes(20))
    table.put(put)
    val put2 = Put(Bytes.toBytes("row2"))
    put2.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("Bobby"))
    put2.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes(33))
    table.put(put2)
    val put3 = Put(Bytes.toBytes("row3"))
    put3.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("Cindy"))
    put3.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes(19))
    table.put(put3)

    // scan
    val scan = Scan()
    val resultScanner = table.getScanner(scan)
    resultScanner.forEach { result ->
        val scanName = Bytes.toString(result.getValue(Bytes.toBytes("f"), Bytes.toBytes("name")))
        val scanAge = Bytes.toInt(result.getValue(Bytes.toBytes("f"), Bytes.toBytes("age")))
        println("scan name: $scanName")
        println("scan age: $scanAge")
    }
}

fun filter(table: Table) {
    // prepare
    val put = Put(Bytes.toBytes("row1"))
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("Alice"))
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes(22))
    table.put(put)
    val put2 = Put(Bytes.toBytes("row2"))
    put2.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("Bobby"))
    put2.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes(33))
    table.put(put2)
    val put3 = Put(Bytes.toBytes("row3"))
    put3.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("Cindy"))
    put3.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes(19))
    table.put(put3)

    // row filter
    val scan = Scan()
    val rowFilter = RowFilter(CompareFilter.CompareOp.EQUAL, BinaryComparator(Bytes.toBytes("row2")))
    scan.filter = rowFilter
    val resultRowFilter = table.getScanner(scan)
    resultRowFilter.forEach { result ->
        val scanName = Bytes.toString(result.getValue(Bytes.toBytes("f"), Bytes.toBytes("name")))
        val scanAge = Bytes.toInt(result.getValue(Bytes.toBytes("f"), Bytes.toBytes("age")))
        println("scan name(row filter): $scanName")
        println("scan age(row filter): $scanAge")
    }

    // qualifier filter
    val qualifierFilter = QualifierFilter(CompareFilter.CompareOp.EQUAL, BinaryComparator(Bytes.toBytes("name")))
    scan.filter = qualifierFilter
    val resultQualifierFilter = table.getScanner(scan)
    resultQualifierFilter.forEach { result ->
        val scanName = Bytes.toString(result.getValue(Bytes.toBytes("f"), Bytes.toBytes("name")))
        var scanAge: Int? = null
        if (result.containsColumn(Bytes.toBytes("f"), Bytes.toBytes("age"))) {
            scanAge = Bytes.toInt(result.getValue(Bytes.toBytes("f"), Bytes.toBytes("age")) ?: Bytes.toBytes(-1))
        }
        println("scan name(qualifier filter): $scanName")
        println("scan age(qualifier filter): $scanAge")
    }

    // value filter
    val valueFilter = ValueFilter(CompareFilter.CompareOp.GREATER, BinaryComparator(Bytes.toBytes(20)))
    scan.filter = valueFilter
    val resultValueFilter = table.getScanner(scan)
    resultValueFilter.forEach { result ->
        val scanName = Bytes.toString(result.getValue(Bytes.toBytes("f"), Bytes.toBytes("name")))
        var scanAge: Int? = null
        if (result.containsColumn(Bytes.toBytes("f"), Bytes.toBytes("age"))) {
            scanAge = Bytes.toInt(result.getValue(Bytes.toBytes("f"), Bytes.toBytes("age")) ?: Bytes.toBytes(-1))
        }
        println("scan name(value filter): $scanName")
        println("scan age(value filter): $scanAge")
    }
}

fun mutateRow(table: Table) {
    val put = Put(Bytes.toBytes("row1"))
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("Alice"))
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes(20))
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
        val scanAge = Bytes.toInt(result.getValue(Bytes.toBytes("f"), Bytes.toBytes("age")))
        val scanCountry = Bytes.toString(result.getValue(Bytes.toBytes("f"), Bytes.toBytes("country")))
        println("scan name: $scanName")
        println("scan age: $scanAge")
        println("scan country: $scanCountry")
    }
}

fun increment(table: Table) {
    // prepare
    val put = Put(Bytes.toBytes("row1"))
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes(33L))
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("stamina"), Bytes.toBytes(100L))
    table.put(put)

    val increment = Increment(Bytes.toBytes("row1"))
    increment.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), 1L)
    increment.addColumn(Bytes.toBytes("f"), Bytes.toBytes("stamina"), -10L)

    val result1 = table.increment(increment)
    val age1 = Bytes.toLong(result1.getValue(Bytes.toBytes("f"), Bytes.toBytes("age")))
    val stamina1 = Bytes.toLong(result1.getValue(Bytes.toBytes("f"), Bytes.toBytes("stamina")))
    println("age: $age1")
    println("stamina: $stamina1")

    table.increment(increment)
    val result2 = table.increment(increment)
    val age2 = Bytes.toLong(result2.getValue(Bytes.toBytes("f"), Bytes.toBytes("age")))
    val stamina2 = Bytes.toLong(result2.getValue(Bytes.toBytes("f"), Bytes.toBytes("stamina")))
    println("age: $age2")
    println("stamina: $stamina2")
}

fun cas(table: Table) {
    // prepare
    val put = Put(Bytes.toBytes("row1"))
    put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes(20))
    table.put(put)

    // check and put
    val update = Put(Bytes.toBytes("row1"))
    update.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes(18))
    val updateResult = table.checkAndPut(Bytes.toBytes("row1"), Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes(20), update)
    println("update result: $updateResult")

    val get = Get(Bytes.toBytes("row1"))
    get.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"))
    val age = Bytes.toInt(table.get(get).getValue(Bytes.toBytes("f"), Bytes.toBytes("age")))
    println("row1 age: $age")

    // check and delete
    val delete = Delete(Bytes.toBytes("row1"))
    val deleteResult = table.checkAndDelete(Bytes.toBytes("row1"), Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes(18), delete)
    println("delete result: $deleteResult")

    val exists= table.exists(get)
    println("row1 exists?: $exists")
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
