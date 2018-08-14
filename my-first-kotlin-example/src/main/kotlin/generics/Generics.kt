package generics

fun main(args: Array<String>) {
    // cast
    val any: Any = "aaa"
    val any2: Any = 19
    val str = any as String
    val int = any2 as Int
    println("str:$str")
    println("int:$int")

    val some: Something<String> = Something("abc")
    println("something:" + some.value)

    show("xyz")
    show(some)
}

class Something<T>(val value: T)

fun <T> show(value: T) {
    println("show:$value")
}
