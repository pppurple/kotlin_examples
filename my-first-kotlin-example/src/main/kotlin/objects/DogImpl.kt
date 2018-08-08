package objects

class DogImpl(_name: String): Dog {
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
        return DogImpl(name + ':' + d.name)
    }
}
