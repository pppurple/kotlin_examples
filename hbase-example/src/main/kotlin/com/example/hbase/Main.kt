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
        /*
        println("********conn")
        val tableee = TableName.valueOf("myspace:mytable")
        println("********table:" + tableee)
        val t = it.getTable(tableee)
        println("********t:" + t)

        // get
        val get = t.get(Get(Bytes.toBytes("row1")))
        // Print the results
        val value = Bytes.toString(get.getValue(Bytes.toBytes("myfamily"), Bytes.toBytes("name")))
        println("***" + "r1:" + value);


        var r1 = Put(Bytes.toBytes("r1"))
        r1.addColumn(Bytes.toBytes("myfamily"), Bytes.toBytes("test"), Bytes.toBytes("pppp"))
        println("********add:")
        t.put(r1)
        println("********added:")
        t.close()
        println("********closed:")
        */

        val tableName= TableName.valueOf("myspace:people")
        it.getTable(tableName).use { table ->
            // exists
            val getRow = Get(Bytes.toBytes("row1"))
            val existsRow = table.exists(getRow)
            println("row1 exists?: $existsRow")
            getRow.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"))
            val existsCol = table.exists(getRow)
            println("row1 f name exists?: $existsCol")

            // put
            val put = Put(Bytes.toBytes("row1"))
            // row1.add(Bytes.toBytes(""), Bytes.toBytes(""), Bytes.toBytes(""))
            put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("Alice"))
            put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes("20"))
            table.put(put)

            // get
            val get = Get(Bytes.toBytes("row1"))
            get.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"))
            get.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"))
            val getResult = table.get(get)
            val name = Bytes.toString(getResult.getValue(Bytes.toBytes("f"), Bytes.toBytes("name")))
            val age = Bytes.toString(getResult.getValue(Bytes.toBytes("f"), Bytes.toBytes("age")))
            println("row1 name: $name")
            println("row1 age: $age")

            // delete (row)
            val deleteAll = Delete(Bytes.toBytes("row1"))
            table.delete(deleteAll)
            // delete (column)
            table.put(put)
            val delete = Delete(Bytes.toBytes("row1"))
            delete.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"))
            table.delete(delete)

            // scan
            table.delete(deleteAll) // cleanup
            table.put(put)
            val put2 = Put(Bytes.toBytes("row2"))
            put2.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("Bobby"))
            put2.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes("33"))
            table.put(put2)
            val put3 = Put(Bytes.toBytes("row3"))
            put3.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("Cindy"))
            put3.addColumn(Bytes.toBytes("f"), Bytes.toBytes("age"), Bytes.toBytes("19"))
            table.put(put3)

            val scan = Scan()
            val resultScanner = table.getScanner(scan)
            resultScanner.forEach { result ->
                val scanName = Bytes.toString(result.getValue(Bytes.toBytes("f"), Bytes.toBytes("name")))
                val scanAge = Bytes.toString(result.getValue(Bytes.toBytes("f"), Bytes.toBytes("age")))
                println("scan name: $scanName")
                println("scan age: $scanAge")
            }
            /*
            for (result in resultScanner) {
                val scanName = Bytes.toString(result.getValue(Bytes.toBytes("f"), Bytes.toBytes("name")))
                val scanAge = Bytes.toString(result.getValue(Bytes.toBytes("f"), Bytes.toBytes("age")))
                println("scan name: $scanName")
                println("scan age: $scanAge")
            }
            */


            // mutateRow
            table.delete(deleteAll) // cleanup
            val rowMutations = RowMutations(Bytes.toBytes("row1"))
            rowMutations.add(put)
            rowMutations.add(delete)
            put.addColumn(Bytes.toBytes("f"), Bytes.toBytes("country"), Bytes.toBytes("Japan"))
            rowMutations.add(put)
            table.mutateRow(rowMutations)




            val putRow2 = Put(Bytes.toBytes("row2"))
            putRow2.addColumn(Bytes.toBytes("f"), Bytes.toBytes("name"), Bytes.toBytes("Bobby"))
        }
    }
}
