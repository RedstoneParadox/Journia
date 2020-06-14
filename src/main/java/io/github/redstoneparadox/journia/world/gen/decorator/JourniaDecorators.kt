package io.github.redstoneparadox.journia.world.gen.decorator

import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.decorator.CountDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator

object JourniaDecorators {
    val SURFACE_PATCH: Decorator<CountDecoratorConfig> = SurfacePatchDecorator()

    fun registerAll() {
        register("surface_patch", SURFACE_PATCH)
    }

    private fun register(id: String, decorator: Decorator<*>) {
        Registry.register(Registry.DECORATOR, "journia:$id", decorator)
    }
}