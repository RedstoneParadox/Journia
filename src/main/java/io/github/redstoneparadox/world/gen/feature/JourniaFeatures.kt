package io.github.redstoneparadox.world.gen.feature

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.TreeFeatureConfig
import java.util.function.Function

object JourniaFeatures {
    private val PINE_LOG: BlockState = Blocks.SPRUCE_LOG.defaultState
    private val PINE_LEAVES: BlockState = Blocks.SPRUCE_LEAVES.defaultState

    val PINE_TREE = PineTreeFeature(Function { JourniaTreeFeatureConfig.deserialize(it) })

    fun registerAll() {
        register("pine_tree", PINE_TREE)
    }

    private fun register(id: String, feature: Feature<*>) {
        Registry.register(Registry.FEATURE, "journia:$id", feature)
    }
}