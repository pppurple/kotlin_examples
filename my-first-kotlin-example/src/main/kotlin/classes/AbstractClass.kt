package classes

abstract class Bird(val name: String, val voice: String) {
    abstract fun cry()
}

class Owl(name: String, voice: String): Bird(name, voice) {
    override fun cry() {
        println("$voice $voice!!")
    }
}

fun main(args: Array<String>) {
    val owl = Owl("pi", "kyu")
    owl.cry()
}
