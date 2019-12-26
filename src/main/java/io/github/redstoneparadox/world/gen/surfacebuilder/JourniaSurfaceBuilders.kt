package io.github.redstoneparadox.world.gen.surfacebuilder

import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

object JourniaSurfaceBuilders {
    val PENTA: SurfaceBuilder<PentaSurfaceConfig> = PentaSurfaceBuilder(PentaSurfaceConfig.Companion::deserialize)


    fun registerAll() {
        register("penta", PENTA)
    }

    fun register(id: String, builder: SurfaceBuilder<*>) {
        Registry.register(Registry.SURFACE_BUILDER, "journia:$id", builder)
    }
}