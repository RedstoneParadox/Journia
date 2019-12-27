package io.github.redstoneparadox

import io.github.redstoneparadox.block.JourniaBlocks
import io.github.redstoneparadox.world.biome.JourniaBiomes
import io.github.redstoneparadox.world.gen.feature.JourniaFeatures
import io.github.redstoneparadox.world.gen.surfacebuilder.JourniaSurfaceBuilders

@Suppress("unused")
fun init() {
    println("Hello, world!")
    JourniaBlocks.registerAll()
    JourniaSurfaceBuilders.registerAll()
    JourniaFeatures.registerAll()
    JourniaBiomes.registerAll()
}