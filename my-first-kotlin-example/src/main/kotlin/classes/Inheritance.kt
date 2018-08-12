package classes

open class Animal(val name: String, val voice: String) {
    fun bark() {
        println("$voice!")
    }

    open fun showProfile() {
        println("name:$name")
    }
}

class Dog(name: String, voice: String, val age: Int): Animal(name, voice) {
    override fun showProfile() {
        println("name:$name, age:$age")
    }
}

fun main(args: Array<String>) {
    val dog = Dog("john", "bow", 5)
    dog.bark()
    dog.showProfile()

}
