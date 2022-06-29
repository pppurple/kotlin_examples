package intersection

fun main() {
    val implString = JavaInterfaceImplUsingIntersection<String>()
    implString.put("abc")
    implString.put("")
    // error
    // implString.put(null)

    val implStringNullable = JavaInterfaceImplUsingIntersection<String?>()
    implStringNullable.put("abc")
    implStringNullable.put("")
    // error
    // implStringNullable.put(null)

    println("0: " + implStringNullable.get(0))
    println("1: " + implStringNullable.get(1))

    implStringNullable.putNullable(null)
    // error: NullPointerException
    // println("2: " + implStringNullable.get(2))
    println("2: " + implStringNullable.getNullable(2))
}
