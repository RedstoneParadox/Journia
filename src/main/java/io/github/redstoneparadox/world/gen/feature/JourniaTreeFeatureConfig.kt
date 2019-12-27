package io.github.redstoneparadox.world.gen.feature

import com.mojang.datafixers.Dynamic
import com.mojang.datafixers.types.DynamicOps
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.stateprovider.SimpleStateProvider

class JourniaTreeFeatureConfig(
    val trunk: BlockState,
    val leaves: BlockState,
    val minHeight: Int,
    val maxHeight: Int
): TreeFeatureConfig(SimpleStateProvider(trunk), SimpleStateProvider(leaves), null, minHeight) {
    override fun <T : Any?> serialize(ops: DynamicOps<T>): Dynamic<T> {
        return Dynamic(
            ops,
            ops.createMap(
                mutableMapOf(
                    ops.createString("trunk") to BlockState.serialize(ops, trunk).value,
                    ops.createString("leaves") to BlockState.serialize(ops, leaves).value,
                    ops.createString("min_height") to ops.createInt(minHeight),
                    ops.createString("max_height") to ops.createInt(maxHeight)
                )
            )
        )
    }

    companion object {
        fun <T> deserialize(dynamic: Dynamic<T>): JourniaTreeFeatureConfig {
            val trunk = dynamic["trunk"].map { BlockState.deserialize(it) }.orElse(Blocks.OAK_LOG.defaultState)
            val leaves = dynamic["leaves"].map { BlockState.deserialize(it) }.orElse(Blocks.OAK_LEAVES.defaultState)
            val minHeight = dynamic["min_height"].map {
                val value = it.value
                 if (value is Int) value else 3
            }.orElse(3).asElse(3)
            val maxHeight = dynamic["max_height"].map {
                val value = it.value
                if (value is Int) value else 3
            }.orElse(3).asElse(3)
            return JourniaTreeFeatureConfig(trunk, leaves, minHeight, maxHeight)
        }

        private inline fun <reified T> Any?.asElse(t: T): T = if (this is T) this else t
    }
}