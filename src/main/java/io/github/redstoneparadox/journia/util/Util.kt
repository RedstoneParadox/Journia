package io.github.redstoneparadox.journia.util

fun <E> MutableCollection<E>.concat(other: Collection<E>): MutableCollection<E> {
    this.addAll(other)
    return this
}

fun <E> MutableList<E>.concat(other: Collection<E>): MutableList<E> {
    this.addAll(other)
    return this
}

inline fun <T> T.then(func: (T) -> Unit): T {
    func(this)
    return this
}