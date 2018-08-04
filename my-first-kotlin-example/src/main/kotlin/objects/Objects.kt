package objects

fun main(args: Array<String>) {
    // String
    val s = "Hello"
    println("s:$s")
    println("s[1]:" + s[1])

    val ss = """
        abc
        efg
    """.trimIndent()
    println("ss:$ss")

    val sss = """
        |ABC
        |EFG
    """.trimMargin()
    println("sss:$sss")

    // Array
    val ints = arrayOfNulls<Int>(10)
    ints[0] = 123
    println("ints[0]:" + ints[0])
    println("ints[1]:" + ints[1])
    println("size:" + ints.size)

    val ints2 = arrayOf(10, 20, 30)
    println("ints[0]:" + ints2[0])
    println("ints[1]:" + ints2[1])
    println("size:" + ints2.size)

    // List
    val intList: List<Int> = listOf(11, 22, 33)
    println("intList[0]:" + intList.get(0))
    println("intList[0]:" + intList[0])
    println("size:" + intList.size)

    val strList: MutableList<String> = mutableListOf("aaa", "bbb", "ccc")
    println("strList[1]:" + strList.get(1))
    println("strList[1]:" + strList[1])
    println("size:" + strList.size)

    // Set
    val intSet: Set<Int> = setOf(7, 8, 9, 8, 9)
    println("intSet:$intSet")
    println("set:" + intSet.size)

    val strSet: MutableSet<String> = mutableSetOf("AA", "BB", "CC", "AA")
    println("strSet:$strSet")
    println("size:" + strSet.size)

    // Map
    val strMap: Map<Int, String> = mapOf(
            100 to "AAA",
            200 to "BBB"
    )
    println("strMap:" + strMap[200])
    println("size:" + strMap.size)

    val intMap: MutableMap<String, Int> = mutableMapOf(
            "aa" to 11,
            "bb" to 22
    )
    println("intMap:" + intMap["bb"])
    println("size:" + intMap.size)

    // Range
    val range: IntRange = 100 .. 200
    println("is in:" + (101 in range))
    println("is in:" + (201 in range))

    val listFromRange: List<Int> = range.toList()
    println("listFromRange:" + listFromRange[1])
    println("size:" + listFromRange.size)

    val listDown: List<Int> = (200 downTo 100).toList()
    println("listDown:" + listDown[1])
    println("size:" + listDown.size)
}
