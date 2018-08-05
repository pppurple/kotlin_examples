package function

fun double(i: Int): Int = i * i

fun with(s: String, c: Char): String = c + s + c

// default args
fun withDefault(s: String, c: Char = '!'): String = c + s + c

// variable argument
fun join(vararg strs: String): String {
    var joined = ""
    for (s in strs) joined += s
    return joined
}

// recursive function (not recommend)
fun sum(ints: List<Int>): Int =
        if (ints.isEmpty()) 0
        else ints.first() + sum(ints.drop(1))

// recursive function (Tail Call Optimization)
tailrec fun sum2(ints: List<Int>, accumulator: Int = 0): Int =
        if (ints.isEmpty()) accumulator
        else sum2(ints.drop(1), accumulator + ints.first())

// recursive function with local function
fun sum3(ints: List<Int>): Int {
    tailrec fun local(ints: List<Int>, accumulator: Int): Int =
            if (ints.isEmpty()) accumulator
            else local(ints.drop(1), accumulator + ints.first())
    return local(ints, 0)
}

fun main(args: Array<String>) {
    println(double(2))

    // call with arg name
    println(with(s = "AAA", c = ':'))
    println(with(c = ':', s = "AAA"))

    // default args
    println(withDefault("BBB"))

    // variable argument
    println(join("aa", "bb", "cc"))
    val strs = arrayOf("AA", "BB", "CC")
    println(join(*strs))

    // recursive function
    println(sum((1..10).toList()))
    println(sum2((1..10).toList()))
    println(sum3((1..10).toList()))
}
