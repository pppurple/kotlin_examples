package classes

fun main(args: Array<String>) {
    // Person
    val ami = Person()
    ami.name = "Ami"
    println(ami.initial)

    // Position
    val pos = Position(10, 20)
    println(pos.yd)

    // Tri
    val tri = Tri(100, 200)
    println(tri.height)
    val tri2 = Tri(50)
    println(tri2.height)

    // TriWithDefo
    val triWithDefo = TriWithDefo(30)
    println(triWithDefo.height)

    // Pig
    val pig = Pig("boo", 20)

    // extension
    println("abc".upperRepeat(5))
}

class Person {
    var name: String = "test"
        set(value) {
            println("set name:$value")
            field = value
        }
    var age: Int = 19
    var country: String = "America"
    // backing filed
    val initial: Char
        get() = this.name[0]
}

class Position constructor(x: Int, y: Int) {
    val xd: Int = x
    val yd: Int = y
}

// primary constructor
// constructor args to be properties
class Tri(val width: Int, val height: Int) {
    // secondly constructor
    constructor(width: Int): this(width, 10)
}

// primary constructor with default value
class TriWithDefo(val width: Int, val height: Int = 10)

// initializer
class Pig(val name: String, val age: Int) {
    init {
        println("name: $name")
    }
}

// extension
fun String.upperRepeat(n: Int): String =
    this.repeat(n).toUpperCase()
