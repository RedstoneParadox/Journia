package io.github.redstoneparadox.journia.util

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

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

fun <T, O: Any> Codec<T>.field(name: String, getter: O.() -> T): RecordCodecBuilder<O, T> {
    return this.fieldOf(name).forGetter<O> { it.getter() }
}