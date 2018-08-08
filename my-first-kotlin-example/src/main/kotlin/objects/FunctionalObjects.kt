package objects

fun double(int: Int): Int = 2 * int

fun add(str1: String, str2: String): String = "$str1:$str2"

fun calc(int1: Int, int2: Int, acc: (Int, Int) -> Int): Int {
    return acc(int1, int2);
}

fun tasu(int1: Int, int2: Int): Int = int1 + int2
fun hiku(int1: Int, int2: Int): Int = int1 - int2

inline fun doubleUnless100(int: () -> Int): Int {
    if (int() < 100) {
        return 2 * int()
    }
    return int()
}


fun main(args: Array<String>) {
    val func = ::double
    println(func(10))

    // specify types
    val func2: (Int) -> Int = ::double
    println(func2(10))
    val funcAdd: (String, String) -> String = ::add
    println(funcAdd("AAA", "BBB"))

    // calc
    println(calc(10, 20, ::tasu))
    println(calc(10, 20, ::hiku))

    // calc with lambda
    val tasuLambda = {int1: Int, int2: Int -> int1 + int2}
    val hikuLambda = {int1: Int, int2: Int -> int1 - int2}
    println(calc(10, 20, tasuLambda))
    println(calc(10, 20, hikuLambda))
    println(calc(10, 20) { int1: Int, int2: Int -> int1 + int2})
    println(calc(10, 20) { int1: Int, int2: Int -> int1 - int2})

    // inline
    println(doubleUnless100({88}))
    println(doubleUnless100({101}))

    // anonymous function
    val ano: (String) -> String = fun(s: String) = s + s
    println(ano("abc"))
}
