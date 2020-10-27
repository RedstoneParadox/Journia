package io.github.redstoneparadox.journia.world.gen.feature

import io.github.redstoneparadox.journia.world.gen.decorator.JourniaDecorators
import net.minecraft.util.Identifier
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.world.gen.CountConfig
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.ConfiguredFeatures
import net.minecraft.world.gen.feature.FeatureConfig

object JourniaDecoratedFeatures {

    // Rocks
    val LARGE_ROCK_80: ConfiguredFeature<*, *>
    val LARGE_ROCK_60: ConfiguredFeature<*, *>
    // Trees
    val DENSE_PINE_TREE: ConfiguredFeature<*, *>
    val SPARSE_VANILLA_PINE_TREE: ConfiguredFeature<*, *>
    val SPARSE_SPRUCE_TREE: ConfiguredFeature<*, *>
    val SPARSE_GIANT_PINE_TREE: ConfiguredFeature<*, *>
    // Surface Patches
    val COARSE_DIRT_PATCH_4: ConfiguredFeature<*, *>
    val STONE_PATCH_4: ConfiguredFeature<*, *>

    init {
        LARGE_ROCK_80 = register("large_rock_80",
            JourniaConfiguredFeatures.LARGE_ROCK_FORMATION.decorate(
                JourniaDecorators.RANDOM_HEIGHTMAP.configure(
                    ChanceDecoratorConfig(80)
                )
            )
        )
        LARGE_ROCK_60 = register("large_rock_60",
            JourniaConfiguredFeatures.LARGE_ROCK_FORMATION.decorate(
                JourniaDecorators.RANDOM_HEIGHTMAP.configure(
                    ChanceDecoratorConfig(60)
                )
            )
        )

        DENSE_PINE_TREE = register("dense_pine_tree",
            JourniaConfiguredFeatures.PINE_TREE
                .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
                .decorate(
                    Decorator.COUNT_EXTRA.configure(CountExtraDecoratorConfig(4, 0.1f, 1))
                )
        )
        SPARSE_VANILLA_PINE_TREE = register("sparse_vanilla_pine_tree",
            ConfiguredFeatures.PINE
                .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
                .decorate(
                    Decorator.COUNT_EXTRA.configure(CountExtraDecoratorConfig(2, 0.1f, 1))
                )
        )
        SPARSE_SPRUCE_TREE = register("sparse_spruce_tree",
            ConfiguredFeatures.SPRUCE
                .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
                .decorate(
                    Decorator.COUNT_EXTRA.configure(CountExtraDecoratorConfig(2, 0.1f, 1))
                )
        )
        SPARSE_GIANT_PINE_TREE = register("sparse_giant_pine_tree",
            JourniaConfiguredFeatures.GIANT_PINE_TREE
                .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
                .decorate(
                    Decorator.COUNT_EXTRA.configure(CountExtraDecoratorConfig(2, 0.1f, 1))
                )
        )

        COARSE_DIRT_PATCH_4 = register("coarse_dirt_patch_4",
            JourniaConfiguredFeatures.COARSE_DIRT_PATCH.decorate(
                JourniaDecorators.SURFACE_PATCH.configure(CountConfig(4))
            )
        )
        STONE_PATCH_4 = register("stone_patch_4",
            JourniaConfiguredFeatures.COARSE_DIRT_PATCH.decorate(
                JourniaDecorators.SURFACE_PATCH.configure(CountConfig(4))
            )
        )
    }

    private fun <FC : FeatureConfig> register(name: String, decoratedFeature: ConfiguredFeature<FC, *>): ConfiguredFeature<FC, *> {
        val id = Identifier("journia", name)

        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, id, decoratedFeature)

        return decoratedFeature
    }
}