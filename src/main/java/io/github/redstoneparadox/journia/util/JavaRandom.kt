package io.github.redstoneparadox.journia.util

import kotlin.random.Random

class JavaRandom(val wrapped: java.util.Random): Random() {
    override fun nextBits(bitCount: Int): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun nextBoolean(): Boolean {
        return wrapped.nextBoolean()
    }

    override fun nextInt(): Int {
        return wrapped.nextInt()
    }

    override fun nextInt(until: Int): Int {
        return wrapped.nextInt(until)
    }

    override fun nextInt(from: Int, until: Int): Int {
        return wrapped.nextInt(until)
    }
}

fun java.util.Random.wrap(): JavaRandom {
    return JavaRandom(this)
}