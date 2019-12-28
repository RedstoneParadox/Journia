package io.github.redstoneparadox.journia

import com.mojang.datafixers.types.DynamicOps
import io.github.redstoneparadox.journia.block.JourniaBlocks
import io.github.redstoneparadox.journia.item.JourniaItems
import io.github.redstoneparadox.journia.world.biome.JourniaBiomes
import io.github.redstoneparadox.journia.world.gen.decorator.JourniaDecorators
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders

@Suppress("unused")
fun init() {
    println("Hello, world!")
    JourniaBlocks.registerAll()
    JourniaItems.registerAll()
    JourniaDecorators.registerAll()
    JourniaSurfaceBuilders.registerAll()
    JourniaFeatures.registerAll()
    JourniaBiomes.registerAll()
}

inline fun <reified T> Any?.asElse(t: T): T = if (this is T) this else t

fun <T> String.into(ops: DynamicOps<T>): T {
    return ops.createString(this)
}

fun colorToInt(r: Double, g: Double, b: Double): Int {
    val redInt = r.toInt() * 255
    val greenInt = g.toInt() * 255
    val blueInt = b.toInt() * 255

    return redInt shl 16 or (greenInt shl 8) or blueInt
}