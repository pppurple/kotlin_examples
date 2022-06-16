package intersection

import JavaInterface

class JavaInterfaceImplUsingIntersection<T> : JavaInterface<T> {
    private val list = mutableListOf<T>()

    override fun put(value: T & Any) {
        list.add(value)
    }

    override fun get(): T & Any {
        return list[list.lastIndex]!!
    }
}
