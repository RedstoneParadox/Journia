package io.github.redstoneparadox.world.gen.feature

import io.github.redstoneparadox.world.gen.foliage.PineFoliagePlacer
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig

import net.minecraft.world.gen.stateprovider.SimpleStateProvider

object JourniaFeatures {
    private val SPRUCE_LOG: BlockState = Blocks.SPRUCE_LOG.defaultState;
    private val SPRUCE_LEAVES: BlockState = Blocks.SPRUCE_LEAVES.defaultState

    private val PINE_LOG: BlockState = Blocks.DIAMOND_BLOCK.defaultState

    val PINE_TREE_CONFIG: BranchedTreeFeatureConfig = BranchedTreeFeatureConfig.Builder(SimpleStateProvider(PINE_LOG), SimpleStateProvider(SPRUCE_LEAVES), PineFoliagePlacer(1, 0))
        .baseHeight(6)
        .heightRandA(3)
        .trunkHeight(1)
        .trunkHeightRandom(1)
        .trunkTopOffsetRandom(2)
        .noVines()
        .build()
}