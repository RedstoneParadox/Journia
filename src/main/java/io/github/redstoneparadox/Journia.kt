package io.github.redstoneparadox

import io.github.redstoneparadox.block.JourniaBlocks
import io.github.redstoneparadox.item.JourniaItems
import io.github.redstoneparadox.world.biome.JourniaBiomes
import io.github.redstoneparadox.world.gen.feature.JourniaFeatures
import io.github.redstoneparadox.world.gen.surfacebuilder.JourniaSurfaceBuilders

@Suppress("unused")
fun init() {
    println("Hello, world!")
    JourniaBlocks.registerAll()
    JourniaItems.registerAll()
    JourniaSurfaceBuilders.registerAll()
    JourniaFeatures.registerAll()
    JourniaBiomes.registerAll()
}