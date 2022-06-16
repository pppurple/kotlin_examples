package intersection

import upper_bounds.JavaInterfaceImplUsingUpperBounds

fun main() {
    val implString = JavaInterfaceImplUsingIntersection<String>()
    implString.put("abc")
    implString.put("")
    // error
    // implString.put(null)

    // error
    val implStringNullable = JavaInterfaceImplUsingIntersection<String?>()
    implStringNullable.put("abc")
    implStringNullable.put("")
    // error
    // implStringNullable.put(null)
    val value = implStringNullable.get()
}
