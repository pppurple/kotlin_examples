package intersection

import JavaInterface

class JavaInterfaceImplUsingIntersection<T> : JavaInterface<T> {
    private val list = mutableListOf<T>()

    override fun put(value: T & Any) {
        list.add(value)
    }

    fun putNullable(value: T) {
        list.add(value)
    }

    override fun get(index: Int): T & Any {
        return list[index]!!
    }

    fun getNullable(index: Int): T {
        return list[index]
    }
}
