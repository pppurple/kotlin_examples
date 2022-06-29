package upper_bounds

fun main() {
    val implString = JavaInterfaceImplUsingUpperBounds<String>()
    implString.put("abc")
    implString.put("")
    // error
    // implString.put(null)

    // error
    // val implStringNullable = JavaInterfaceImplUsingUpperBounds<String?>()
}
