package io.github.redstoneparadox.journia

import io.github.redstoneparadox.journia.item.JourniaItems
import io.github.redstoneparadox.journia.block.JourniaBlocks
import io.github.redstoneparadox.journia.world.biome.JourniaBiomes
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures

@Suppress("unused")
fun init() {
    println("Hello, world!")
    JourniaBlocks.registerAll()
    JourniaItems.registerAll()
    JourniaSurfaceBuilders.registerAll()
    JourniaFeatures.registerAll()
    JourniaBiomes.registerAll()
}