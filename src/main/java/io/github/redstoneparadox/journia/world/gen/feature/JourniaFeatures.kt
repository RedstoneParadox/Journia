package io.github.redstoneparadox.journia.world.gen.feature

import io.github.redstoneparadox.journia.block.JourniaBlocks
import io.github.redstoneparadox.journia.world.gen.foliage.CubenFoliagePlacer
import io.github.redstoneparadox.journia.world.gen.foliage.PineFoliagePlacer
import io.github.redstoneparadox.journia.world.gen.trunk.CubenTrunkPlacer
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.state.property.Properties
import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.TreeFeature
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider
import net.minecraft.world.gen.trunk.StraightTrunkPlacer

object JourniaFeatures {
    private val PINE_LOG: BlockState = JourniaBlocks.PINE_LOG.defaultState
    private val PINE_LEAVES: BlockState = JourniaBlocks.PINE_LEAVES.defaultState.with(Properties.PERSISTENT, true)

    val PINE_TREE_CONFIG = pineTreeConfig(PINE_LOG, PINE_LEAVES, 9, 2, 3)
    val DEAD_TREE_CONFIG = DeadTreeFeatureConfig(Blocks.OAK_LOG.defaultState, 4, 2)
    val DEAD_BIRCH_TREE_CONFIG = DeadTreeFeatureConfig(Blocks.BIRCH_LOG.defaultState, 4, 2)

    val PINE_TREE: TreeFeature = TreeFeature(TreeFeatureConfig.CODEC)
    val DEAD_TREE = DeadTreeFeature()
    val SURFACE_PATCH = SurfacePatchFeature()
    val BLOCK_BANDS = BlockBandsFeature()
    val ROCK_FORMATION = RockFormationFeature()

    fun registerAll() {
        register("pine_tree", PINE_TREE)
        register("dead_tree", DEAD_TREE)
        register("surface_patch", SURFACE_PATCH)
        register("block_bands", BLOCK_BANDS)
        register("rock_formation", ROCK_FORMATION)
    }

    private fun register(id: String, feature: Feature<*>) {
        Registry.register(Registry.FEATURE, "journia:$id", feature)
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

    fun cubenTreeConfig(): TreeFeatureConfig {
        return TreeFeatureConfig.Builder(
            SimpleBlockStateProvider(JourniaBlocks.CUBEN_LOG.defaultState),
            SimpleBlockStateProvider(JourniaBlocks.CUBEN_LEAVES.defaultState.with(Properties.PERSISTENT, true)),
            CubenFoliagePlacer(3, 0, 0, 0),
            CubenTrunkPlacer(5, 10, 0),
            TwoLayersFeatureSize(3, 0, 3)
        )
            .build()
    }
}