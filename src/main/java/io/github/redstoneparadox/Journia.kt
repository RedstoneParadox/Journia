package io.github.redstoneparadox

import io.github.redstoneparadox.world.gen.surfacebuilder.JourniaSurfaceBuilders

@Suppress("unused")
fun init() {
    println("Hello, world!")
    JourniaSurfaceBuilders.registerAll()
}