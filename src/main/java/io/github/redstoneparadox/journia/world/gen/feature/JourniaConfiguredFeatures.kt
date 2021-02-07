package io.github.redstoneparadox.journia.world.gen.feature

import com.google.common.collect.ImmutableList
import io.github.redstoneparadox.journia.block.JourniaBlocks
import io.github.redstoneparadox.journia.shape.ConfiguredShapeProviders
import io.github.redstoneparadox.journia.world.gen.foliage.MegaPineFoliagePlacer
import io.github.redstoneparadox.journia.world.gen.foliage.PineFoliagePlacer
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.state.property.Properties
import net.minecraft.util.Identifier
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.world.gen.ProbabilityConfig
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider
import net.minecraft.world.gen.tree.AlterGroundTreeDecorator
import net.minecraft.world.gen.tree.TreeDecorator
import net.minecraft.world.gen.trunk.GiantTrunkPlacer
import net.minecraft.world.gen.trunk.StraightTrunkPlacer

object JourniaConfiguredFeatures {
    // Blockstates
    private val PINE_LOG: BlockState = JourniaBlocks.PINE_LOG.defaultState
    private val PINE_LEAVES: BlockState = JourniaBlocks.PINE_LEAVES.defaultState.with(Properties.PERSISTENT, true)

    // Surface Patches
    val COARSE_DIRT_PATCH: ConfiguredFeature<*, *>
    val LARGE_COARSE_DIRT_PATCH: ConfiguredFeature<*, *>
    val STONE_PATCH: ConfiguredFeature<*, *>
    val GRASS_PATCH: ConfiguredFeature<*, *>
    val MUD_PATCH: ConfiguredFeature<*, *>
    // Trees
    val PINE_TREE: ConfiguredFeature<TreeFeatureConfig, *>
    val GIANT_PINE_TREE: ConfiguredFeature<TreeFeatureConfig, *>
    // Other
    val LARGE_ROCK_FORMATION: ConfiguredFeature<*, *>
    // Vegetation
    val SEAGRASS_HALF: ConfiguredFeature<*, *>
    val SEAGRASS_TALL: ConfiguredFeature<*, *>
    val SEAGRASS_SHORT: ConfiguredFeature<*, *>

    init {
        COARSE_DIRT_PATCH = register("coarse_dirt_patch",
            JourniaFeatures.SURFACE_PATCH.configure(
                SurfacePatchFeatureConfig(Blocks.COARSE_DIRT.defaultState,
                    0.3,
                    listOf(
                        Blocks.GRASS_BLOCK.defaultState
                    ),
                    below = true
                ),
            )
        )
        LARGE_COARSE_DIRT_PATCH = register("large_coarse_dirt_patch",
            JourniaFeatures.SURFACE_PATCH.configure(
                SurfacePatchFeatureConfig(Blocks.COARSE_DIRT.defaultState,
                    0.75,
                    listOf(
                        Blocks.GRASS_BLOCK.defaultState,
                        Blocks.STONE.defaultState
                    ),
                    below = true
                )
            )
        )
        STONE_PATCH = register("stone_patch",
            JourniaFeatures.SURFACE_PATCH.configure(
                SurfacePatchFeatureConfig(
                    Blocks.STONE.defaultState,
                    0.3,
                    listOf(
                        Blocks.GRASS_BLOCK.defaultState,
                        Blocks.DIRT.defaultState,
                        Blocks.COARSE_DIRT.defaultState
                    ),
                    true
                )
            )
        )
        GRASS_PATCH = register("grass_patch",
            JourniaFeatures.SURFACE_PATCH.configure(
                SurfacePatchFeatureConfig(
                    state = Blocks.GRASS_BLOCK.defaultState,
                    coverage = 1.0,
                    targets = listOf(
                        Blocks.SAND.defaultState
                    ),
                    size = 12.0,
                    below = true,
                    integrity = 0.35
                )
            )
        )
        MUD_PATCH = register("mud_patch",
            JourniaFeatures.SURFACE_PATCH.configure(
                SurfacePatchFeatureConfig(
                    state = JourniaBlocks.MUD.defaultState,
                    coverage = 0.5,
                    targets = listOf(
                        Blocks.GRASS_BLOCK.defaultState,
                        Blocks.DIRT.defaultState
                    ),
                    below = true,
                    integrity = 0.65
                )
            )
        )

        PINE_TREE = registerTree("pine_tree", pineTreeConfig(PINE_LOG, PINE_LEAVES, 9, 2, 3))
        GIANT_PINE_TREE = registerTree("giant_pine_tree", giantPineTreeConfig())

        LARGE_ROCK_FORMATION = register("large_rock_formation", JourniaFeatures.ROCK_FORMATION.configure(
                RockFormationFeatureConfig(
                    SimpleBlockStateProvider(Blocks.STONE.defaultState),
                    ConfiguredShapeProviders.ROCK_SHAPE_PROVIDER,
                    3..6,
                    5..9
                )
            )
        )

        SEAGRASS_HALF = register("seagrass_half",
            Feature.SEAGRASS.configure(
                ProbabilityConfig(
                    0.5f
                )
            )
        )
        SEAGRASS_TALL = register("seagrass_tall",
            Feature.SEAGRASS.configure(
                ProbabilityConfig(
                    1.0f
                )
            )
        )
        SEAGRASS_SHORT = register("seagrass_short",
            Feature.SEAGRASS.configure(
                ProbabilityConfig(
                    0.0f
                )
            )
        )
    }

    private fun <FC : FeatureConfig> register(name: String, configuredFeature: ConfiguredFeature<FC, *>): ConfiguredFeature<FC, *> {
        val id = Identifier("journia", name)

        return BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, id, configuredFeature)
    }

    private fun registerTree(name: String, config: TreeFeatureConfig): ConfiguredFeature<TreeFeatureConfig, *> {
        return register(name, Feature.TREE.configure(config))
    }

    private fun pineTreeConfig(trunk: BlockState, leaves: BlockState, baseHeight: Int, randomHeight: Int, foliageHeight: Int): TreeFeatureConfig {
        return TreeFeatureConfig.Builder(
            SimpleBlockStateProvider(trunk),
            SimpleBlockStateProvider(leaves),
            PineFoliagePlacer(3, 0, 0, 0),
            StraightTrunkPlacer(baseHeight, randomHeight, 0),
            TwoLayersFeatureSize(2, 0, 2)
        )
            .build()
    }

    private fun giantPineTreeConfig(): TreeFeatureConfig {
        return TreeFeatureConfig.Builder(
            SimpleBlockStateProvider(PINE_LOG),
            SimpleBlockStateProvider(PINE_LEAVES),
            MegaPineFoliagePlacer(12, 6, 5, 0, 0, 0),
            GiantTrunkPlacer(25, 4, 0),
            TwoLayersFeatureSize(1, 1, 2)
        ).decorators(ImmutableList.of(AlterGroundTreeDecorator(SimpleBlockStateProvider(Blocks.PODZOL.defaultState))) as List<TreeDecorator>)
            .build()
    }
}