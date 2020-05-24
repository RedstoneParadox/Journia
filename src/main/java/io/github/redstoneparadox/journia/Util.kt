package io.github.redstoneparadox.journia

fun <E> MutableCollection<E>.concat(other: Collection<E>): MutableCollection<E> {
    this.addAll(other)
    return this
}

fun <E> MutableList<E>.concat(other: Collection<E>): MutableList<E> {
    this.addAll(other)
    return this
}