package io.github.redstoneparadox.journia.shape

import com.mojang.serialization.Codec
import com.terraformersmc.terraform.shapes.api.Position
import com.terraformersmc.terraform.shapes.api.Shape
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import java.util.*

class ConfiguredShapeProvider<C: ShapeProviderConfig, P: ShapeProvider<C>>(private val config: C, private val provider: P) {
    fun get(random: Random): Shape {
        return provider.get(random, config)
    }

    companion object {
        val DEFAULT_IDENTIFIER: Identifier = Identifier("journia:empty")
        val DEFAULT = ConfiguredShapeProvider(ShapeProviderConfig.DEFAULT, ShapeProvider { random, config ->
            return@ShapeProvider Shape.of({ false }, Position.of(0.0, 0.0, 0.0), Position.of(1.0, 1.0, 1.0))
        })
        val REGISTRY: Registry<ConfiguredShapeProvider<*, *>> = FabricRegistryBuilder.createDefaulted(ConfiguredShapeProvider::class.java, Identifier("journia:shape_provider"), DEFAULT_IDENTIFIER)
            .buildAndRegister()
        val CODEC: Codec<ConfiguredShapeProvider<*, *>> = Identifier.CODEC.xmap(
            { id -> REGISTRY.get(id) },
            { provider -> REGISTRY.getId(provider) }
        )

        init {
            Registry.register(REGISTRY, DEFAULT_IDENTIFIER, DEFAULT)
        }
    }
}