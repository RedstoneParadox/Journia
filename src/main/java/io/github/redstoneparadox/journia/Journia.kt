package io.github.redstoneparadox.journia

import com.mojang.serialization.DynamicOps
import io.github.redstoneparadox.journia.block.JourniaBlocks
import io.github.redstoneparadox.journia.entity.JourniaEntityTypes
import io.github.redstoneparadox.journia.item.JourniaItems
import io.github.redstoneparadox.journia.shape.ConfiguredShapeProviders
import io.github.redstoneparadox.journia.world.biome.JourniaBiomes
import io.github.redstoneparadox.journia.world.gen.decorator.JourniaDecorators
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders

@Suppress("unused")
fun init() {
    println("Hello, world!")
    JourniaBlocks.init()
    JourniaItems.init()
    ConfiguredShapeProviders.init()
    JourniaDecorators.init()
    JourniaSurfaceBuilders.init()
    JourniaFeatures.init()
    JourniaBiomes.init()
    JourniaEntityTypes.init()
}

inline fun <reified T> Any?.asElse(t: T): T = if (this is T) this else t

fun <T> String.into(ops: DynamicOps<T>): T {
    return ops.createString(this)
}

fun colorToInt(r: Double, g: Double, b: Double): Int {
    val redInt = (r * 255).toInt()
    val greenInt = (g * 255).toInt()
    val blueInt = (b * 255).toInt()

    return (redInt shl 16) or (greenInt shl 8) or blueInt
}