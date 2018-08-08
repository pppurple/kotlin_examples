package objects

interface Cat {
    fun getName(): String
    fun getAge(): Int
    fun bark()
    fun birthday()
    fun child(c: Cat): Cat
}
