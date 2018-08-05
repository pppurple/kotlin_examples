package function

fun double(i: Int): Int = i * i

fun with(s: String, c: Char): String = c + s + c

// default args
fun withDefault(s: String, c: Char = '!'): String = c + s + c

fun main(args: Array<String>) {
    println(double(2))
    // call with arg name
    println(with(s = "AAA", c = ':'))
    println(with(c = ':', s = "AAA"))
    // default args
    println(withDefault("BBB"))
}
