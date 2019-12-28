package io.github.redstoneparadox.journia

import com.mojang.datafixers.Dynamic
import com.mojang.datafixers.types.DynamicOps
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

inline fun <reified T> Any?.asElse(t: T): T = if (this is T) this else t

fun <T> String.into(ops: DynamicOps<T>): T {
    return ops.createString(this)
}