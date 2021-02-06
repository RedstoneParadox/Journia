package io.github.redstoneparadox.journia.world.gen.decorator

import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.CountConfig
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator

object JourniaDecorators {
    val SURFACE_PATCH: Decorator<CountConfig> = SurfacePatchDecorator()
    val RANDOM_HEIGHTMAP: Decorator<ChanceDecoratorConfig> = RandomHeightmapDecorator()

    fun init() {
        register("surface_patch", SURFACE_PATCH)
        register("random_heightmap", RANDOM_HEIGHTMAP)
    }

    private fun register(id: String, decorator: Decorator<*>) {
        Registry.register(Registry.DECORATOR, "journia:$id", decorator)
    }
}