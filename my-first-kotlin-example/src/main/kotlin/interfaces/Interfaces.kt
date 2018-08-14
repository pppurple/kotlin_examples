package interfaces

interface Animal {
    val name: String
    val voice: String
    fun bark()
    fun bark(v: String)
    fun showProfile()
}

open class Dog: Animal {
    override val name = "taro"
    override val voice = "wan"

    override fun bark() {
        bark(voice)
    }

    override fun bark(v: String) {
        println("$v!!!")
    }

    override fun showProfile() {
        println("name:$name")
    }
}

class Pome: Dog() {
    private val _vs : MutableSet<String> = mutableSetOf()

    val vs: Set<String>
        get() = _vs

    override fun bark(v: String) {
        _vs += v
        super.bark(v)
    }
}

// delegation
class Shiba(override val name: String, override val voice: String) : Animal {
    private val dog: Animal = Dog()
    private val _vs : MutableSet<String> = mutableSetOf()

    val vs: Set<String>
        get() = _vs

    override fun bark() {
        dog.bark()
    }

    override fun bark(v: String) {
        _vs += v
        dog.bark(v)
    }

    override fun showProfile() {
        dog.showProfile()
    }
}

// class delegation
class Akita(private val animal: Animal): Animal by animal {
    private val _vs : MutableSet<String> = mutableSetOf()

    val vs: Set<String>
        get() = _vs

    override fun bark(v: String) {
        _vs += v
        animal.bark(v)
    }
}

fun main(args: Array<String>) {
    val dog = Dog()
    dog.bark()

    // Pome
    val pome = Pome()
    pome.bark("wanwan")
    pome.bark()
    pome.bark("bowbow")
    println(pome.vs)

    // Shiba
    val shiba = Shiba("shiiba", "wawooo")
    shiba.bark("wanwan")
    shiba.bark()
    shiba.bark("bowbow")
    println(shiba.vs)

    // Akita
    val akita = Akita(dog)
    akita.bark("pyo-")
    akita.bark()
    akita.bark("gya-")
    println(akita.vs)
}
