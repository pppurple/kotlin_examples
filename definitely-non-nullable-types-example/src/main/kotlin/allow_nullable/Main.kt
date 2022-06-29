package allow_nullable

fun main(){
    val implString = JavaInterfaceImpl<String>()
    implString.put("abc")
    implString.put("")
    // error
    // implString.put(null)

    val implStringNullable = JavaInterfaceImpl<String?>()
    implStringNullable.put("abc")
    implStringNullable.put("")
    implStringNullable.put(null)
}
