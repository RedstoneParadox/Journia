package io.github.redstoneparadox.journia.world.gen.feature

import com.mojang.datafixers.Dynamic
import com.mojang.datafixers.types.DynamicOps
import io.github.redstoneparadox.journia.into
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.world.gen.feature.FeatureConfig

class SurfacePatchFeatureConfig(val state: BlockState, val startRadius: Int): FeatureConfig {
    override fun <T : Any> serialize(ops: DynamicOps<T>): Dynamic<T> {
        return Dynamic(ops, ops.createMap(
            mutableMapOf(
                "state".into(ops) to BlockState.serialize(ops, state).value,
                "start_radius".into(ops) to ops.createInt(startRadius)
            )
        ))
    }

    companion object {
        fun <T> deserialize(dynamic: Dynamic<T>): SurfacePatchFeatureConfig {
            val state = dynamic["state"].map { BlockState.deserialize(it) }.orElse(Blocks.STONE.defaultState)
            val startRadius = dynamic["start_radius"].map {
                val value = it.value
                if (value is Int) value as Int else 0
            }.orElse(0)

            return SurfacePatchFeatureConfig(state, startRadius)
        }
    }
}