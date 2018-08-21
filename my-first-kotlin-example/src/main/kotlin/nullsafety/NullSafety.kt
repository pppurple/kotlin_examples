package nullsafety

fun main(args: Array<String>) {
    val nullStr: String? = null
    println("nullStr:$nullStr")

    val size10 = checkNum(10)
    if (size10 != null) {
        println(size10.toUpperCase())
    }

    val size5 = checkNum(5)
    println(size5?.toUpperCase())

    val repeated = nullStr?.let { repeat(it) }
    println(repeated)

    // force
    val size4: String = checkNum(4)!!
    println(size4.toUpperCase())

    // elvis
    val sizeMinus4 = checkNum(-4) ?: "default"
    println(sizeMinus4.toUpperCase())
}

fun checkNum(i: Int): String? {
    return if (10 <= i) {
        "big"
    } else if (0 <= i){
        "small"
    } else {
        null
    }
}

fun repeat(s: String): String = s + s
