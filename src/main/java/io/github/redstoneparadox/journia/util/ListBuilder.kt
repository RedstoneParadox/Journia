package io.github.redstoneparadox.journia.util

class ListBuilder<E> {
    private val list: MutableList<E> = mutableListOf()

    fun entries(vararg e: E): ListBuilder<E> {
        list.addAll(e)
        return this
    }

    fun entriesFromFunc(func: () -> List<E>): ListBuilder<E> {
        list.addAll(func())
        return this
    }

    fun build(): List<E> {
        return list
    }
}