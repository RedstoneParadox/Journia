package io.github.redstoneparadox.world.gen.feature

import com.mojang.datafixers.Dynamic
import com.mojang.datafixers.types.DynamicOps
import net.minecraft.block.BlockState
import net.minecraft.world.gen.feature.FeatureConfig

class TreeFeatureConfig(
    val trunk: BlockState,
    val leaves: BlockState,
    val minHeight: Int,
    val maxHeight: Int
): FeatureConfig {
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
}