package allow_nullable

import JavaInterface

class JavaInterfaceImpl<T> : JavaInterface<T> {
    private val list = mutableListOf<T>()

    override fun put(value: T) {
        list.add(value)
    }

    override fun get(): T {
        return list[list.lastIndex]
    }
}
