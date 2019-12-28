package io.github.redstoneparadox.journia.world.gen.feature

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.feature.Feature
import java.util.function.Function

object JourniaFeatures {
    private val PINE_LOG: BlockState = Blocks.SPRUCE_LOG.defaultState
    private val PINE_LEAVES: BlockState = Blocks.SPRUCE_LEAVES.defaultState

    val DEAD_TREE_CONFIG = DeadTreeFeatureConfig(Blocks.OAK_LOG.defaultState, 4, 2)

    val PINE_TREE = PineTreeFeature(Function { JourniaTreeFeatureConfig.deserialize(it) })
    val DEAD_TREE = DeadTreeFeature(Function { DeadTreeFeatureConfig.deserialize(it) })

    fun registerAll() {
        register("pine_tree", PINE_TREE)
    }

    private fun register(id: String, feature: Feature<*>) {
        Registry.register(Registry.FEATURE, "journia:$id", feature)
    }
}