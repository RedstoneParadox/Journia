package io.github.redstoneparadox

import io.github.redstoneparadox.world.biome.JourniaBiomes
import io.github.redstoneparadox.world.gen.surfacebuilder.JourniaSurfaceBuilders

@Suppress("unused")
fun init() {
    println("Hello, world!")
    JourniaSurfaceBuilders.registerAll()
    JourniaBiomes.registerAll()
}