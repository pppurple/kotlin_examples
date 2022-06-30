inline fun <reified T> printClass(t: T) {
    val kclazz = T::class
    println(kclazz.qualifiedName)
}

fun main() {
    printClass("str")
    printClass(123)
    printClass(MyClass("my"))
}

data class MyClass(
    val name: String
)
