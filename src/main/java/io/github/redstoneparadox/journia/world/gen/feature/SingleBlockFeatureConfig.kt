package io.github.redstoneparadox.journia.world.gen.feature

import com.mojang.datafixers.Dynamic
import com.mojang.datafixers.types.DynamicOps
import net.minecraft.block.BlockState
import net.minecraft.world.gen.feature.FeatureConfig

class SingleBlockFeatureConfig(private val state: BlockState): FeatureConfig {

    fun getState(): BlockState = state

    override fun <T : Any> serialize(ops: DynamicOps<T>): Dynamic<T> {
        return Dynamic(ops, ops.createMap(
            mutableMapOf(
                ops.createString("state") to BlockState.serialize(ops, state).value
            )
        ))
    }
}