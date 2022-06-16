package upper_bounds

import JavaInterface

class JavaInterfaceImplUsingUpperBounds<T : Any> : JavaInterface<T> {
    private val list = mutableListOf<T>()

    override fun put(value: T) {
        list.add(value)
    }

    override fun get(): T {
        return list[list.lastIndex]
    }
}
