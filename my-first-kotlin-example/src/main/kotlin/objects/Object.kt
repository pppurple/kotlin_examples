package objects

fun main(args: Array<String>) {
    val cat = object {
        val name = "mei"
        val age = 10

        fun printName() = println("name:$name")
        fun printAge() = println("age:$age")
    }

    cat.printAge()
    cat.printName()

    // implements Cat
    fun cat(name: String): Cat = object: Cat {
        var _age = 8
        val voice = "nya"

        override fun getName(): String {
            return name
        }

        override fun getAge(): Int {
            return _age
        }

        override fun bark() {
            println(voice)
        }

        override fun birthday() {
            this._age++
        }

        override fun child(c: Cat): Cat {
            return cat(name + ':' + c.getName())
        }
    }

    val c1 = cat("pome")
    val c2 = cat("tama")
    println(c1.getName())
    println(c2.getName())
    println(c1.getAge())
    c1.birthday()
    println(c1.getAge())
    println(c1.child(c2).getName())

    // implements Dog
    fun dog(_name: String): Dog = object: Dog {
        override val name = _name
        override var age = 0
        val voice = "nya"

        override fun bark() {
            println(voice)
        }

        override fun birthday() {
            this.age++
        }

        override fun child(d: Dog): Dog {
            return dog(name + ':' + d.name)
        }
    }

    val d1 = dog("pochi")
    val d2 = dog("masaru")
    println(d1.name)
    println(d2.name)
    println(d1.age)
    d1.birthday()
    println(d1.age)
    println(d1.child(d2).name)

    // DogImpl
    val di1 = DogImpl("pochi")
    val di2 = DogImpl("masaru")
    println(di1.name)
    println(di2.name)
    println(di1.age)
    di1.birthday()
    println(di1.age)
    println(di1.child(di2).name)
}
