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
    val WETLANDS_JUNGLE_TREE: ConfiguredFeature<*, *>
    val WETLANDS_TREES_JUNGLE: ConfiguredFeature<*, *>
    // Surface Patches
    val COARSE_DIRT_PATCH_4: ConfiguredFeature<*, *>
    val COARSE_DIRT_PATCH_12: ConfiguredFeature<*, *>
    val STONE_PATCH_4: ConfiguredFeature<*, *>
    val GRASS_PATCH_8: ConfiguredFeature<*, *>
    val LARGE_COARSE_DIRT_PATCH_2: ConfiguredFeature<*, *>
    // Vegetation
    val WETLANDS_JUNGLE_BUSH: ConfiguredFeature<*, *>
    val WETLANDS_GRASS: ConfiguredFeature<*, *>
    val WETLANDS_BAMBOO: ConfiguredFeature<*, *>
    val WETLANDS_BAMBOO_VEGITATION: ConfiguredFeature<*, *>
    val WETLANDS_SEAGRASS: ConfiguredFeature<*, *>

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
        WETLANDS_JUNGLE_TREE = register("wetlands_jungle_trees",
            ConfiguredFeatures.JUNGLE_TREE.decorate(
                Decorator.COUNT_EXTRA.configure(
                    CountExtraDecoratorConfig(
                        10,
                        0.1f,
                        1
                    )
                )
            )
        )
        WETLANDS_TREES_JUNGLE = register("wetlands_trees_jungle",
            ConfiguredFeatures.TREES_JUNGLE.decorate(
                Decorator.COUNT_EXTRA.configure(
                    CountExtraDecoratorConfig(
                        10,
                        0.1f,
                        1
                    )
                )
            )
        )

        COARSE_DIRT_PATCH_4 = register("coarse_dirt_patch_4",
            JourniaConfiguredFeatures.COARSE_DIRT_PATCH.decorate(
                JourniaDecorators.SURFACE_PATCH.configure(CountConfig(4))
            )
        )
        COARSE_DIRT_PATCH_12 = register("coarse_dirt_patch_12",
            JourniaConfiguredFeatures.COARSE_DIRT_PATCH.decorate(
                JourniaDecorators.SURFACE_PATCH.configure(CountConfig(8))
            )
        )
        STONE_PATCH_4 = register("stone_patch_4",
            JourniaConfiguredFeatures.COARSE_DIRT_PATCH.decorate(
                JourniaDecorators.SURFACE_PATCH.configure(CountConfig(4))
            )
        )
        GRASS_PATCH_8 = register("grass_patch_4",
            JourniaConfiguredFeatures.GRASS_PATCH.decorate(
                JourniaDecorators.SURFACE_PATCH.configure(CountConfig(8))
            )
        )
        LARGE_COARSE_DIRT_PATCH_2 = register("large_coarse_dirt_patch_2",
            JourniaConfiguredFeatures.LARGE_COARSE_DIRT_PATCH.decorate(
                JourniaDecorators.SURFACE_PATCH.configure(CountConfig(2))
            )
        )

        WETLANDS_JUNGLE_BUSH = register("wetlands_jungle_bush",
            ConfiguredFeatures.JUNGLE_BUSH.decorate(
                Decorator.COUNT_EXTRA.configure(
                    CountExtraDecoratorConfig(
                        10,
                        0.1f,
                        1
                    )
                )
            )
        )
        WETLANDS_GRASS = register("wetlands_grass",
            ConfiguredFeatures.PATCH_GRASS_JUNGLE.decorate(
                Decorator.COUNT_EXTRA.configure(
                    CountExtraDecoratorConfig(
                        20,
                        0.1f,
                        1
                    )
                )
            )
        )
        WETLANDS_BAMBOO = register("wetlands_bamboo",
            ConfiguredFeatures.BAMBOO.decorate(
                Decorator.COUNT_EXTRA.configure(
                    CountExtraDecoratorConfig(
                        20,
                        0.1f,
                        1
                    )
                )
            )
        )
        WETLANDS_BAMBOO_VEGITATION = register("wetlands_bamboo_vegetation",
            ConfiguredFeatures.BAMBOO_VEGETATION.decorate(
                Decorator.COUNT_EXTRA.configure(
                    CountExtraDecoratorConfig(
                        20,
                        0.1f,
                        1
                    )
                )
            )
        )
        WETLANDS_SEAGRASS = register("wetlands_seagrass",
            JourniaConfiguredFeatures.SEAGRASS_1.decorate(
                JourniaDecorators.SURFACE_PATCH.configure(
                    CountConfig(128)
                )
            )
        )
    }

    private fun <FC : FeatureConfig> register(name: String, decoratedFeature: ConfiguredFeature<FC, *>): ConfiguredFeature<FC, *> {
        val id = Identifier("journia", name)

        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, id, decoratedFeature)

        return decoratedFeature
    }
}