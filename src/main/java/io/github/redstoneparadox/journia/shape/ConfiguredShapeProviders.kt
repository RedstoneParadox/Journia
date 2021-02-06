package io.github.redstoneparadox.journia.shape

import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ConfiguredShapeProviders {
    lateinit var ROCK_SHAPE_PROVIDER: ConfiguredShapeProvider<*, *>;

    fun init() {
        ROCK_SHAPE_PROVIDER = register("rock_shape", ConfiguredShapeProvider(
            RockShapeProviderConfig(
                3..8,
                8..18
            ),
            RockShapeProvider()
        ))
    }

    private fun register(name: String, provider: ConfiguredShapeProvider<*, *>): ConfiguredShapeProvider<*, *> {
        val id = Identifier("journia", name)

        return Registry.register(ConfiguredShapeProvider.REGISTRY, id, provider)
    }
}