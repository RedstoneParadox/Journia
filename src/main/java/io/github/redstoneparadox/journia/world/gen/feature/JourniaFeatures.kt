package io.github.redstoneparadox.journia.world.gen.feature

import io.github.redstoneparadox.journia.block.JourniaBlocks
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.state.property.Properties
import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.foliage.BlobFoliagePlacer
import net.minecraft.world.gen.stateprovider.SimpleStateProvider
import java.util.function.Function

object JourniaFeatures {
    private val PINE_LOG: BlockState = JourniaBlocks.PINE_LOG.defaultState
    private val PINE_LEAVES: BlockState = JourniaBlocks.PINE_LEAVES.defaultState.with(Properties.PERSISTENT, true)

    val PINE_TREE_CONFIG = treeConfig(PINE_LOG, PINE_LEAVES, 8, 10)
    val DEAD_TREE_CONFIG = DeadTreeFeatureConfig(Blocks.OAK_LOG.defaultState, 4, 2)

    val PINE_TREE: PineTreeFeature = PineTreeFeature(Function { BranchedTreeFeatureConfig.deserialize2(it) })
    val DEAD_TREE = DeadTreeFeature(Function { DeadTreeFeatureConfig.deserialize(it) })
    val SURFACE_PATCH = SurfacePatchFeature(Function { SurfacePatchFeatureConfig.deserialize(it) })

    fun registerAll() {
        register("pine_tree", PINE_TREE)
    }

    private fun register(id: String, feature: Feature<*>) {
        Registry.register(Registry.FEATURE, "journia:$id", feature)
    }

    private fun treeConfig(trunk: BlockState, leaves: BlockState, minHeight: Int, maxHeight: Int): BranchedTreeFeatureConfig {
        return BranchedTreeFeatureConfig.Builder(
            SimpleStateProvider(trunk),
            SimpleStateProvider(leaves),
            BlobFoliagePlacer(0, 0)
        )
            .baseHeight(minHeight)
            .heightRandA(maxHeight)
            .foliageHeight(maxHeight + 1)
            .build()
    }
}