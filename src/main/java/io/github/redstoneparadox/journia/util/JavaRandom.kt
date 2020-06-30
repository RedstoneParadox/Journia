package io.github.redstoneparadox.journia.util

import kotlin.random.Random

class JavaRandom(val wrapped: java.util.Random): Random() {
    override fun nextBits(bitCount: Int): Int {
        val next = nextInt()
        return next.ushr(32 - bitCount) and (-bitCount).shr(31)
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
}

fun java.util.Random.wrap(): JavaRandom {
    return JavaRandom(this)
}