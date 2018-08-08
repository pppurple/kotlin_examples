package objects

interface Dog {
    fun bark()
    fun birthday()
    fun child(d: Dog): Dog

    val name: String
    var age: Int
}
