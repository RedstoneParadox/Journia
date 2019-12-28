package io.github.redstoneparadox.journia.world.gen.feature

import com.mojang.datafixers.Dynamic
import com.mojang.datafixers.types.DynamicOps
import io.github.redstoneparadox.journia.asElse
import io.github.redstoneparadox.journia.into
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.world.gen.feature.FeatureConfig

class DeadTreeFeatureConfig(private val trunk: BlockState, private val minHeight: Int, private val additionalHeight: Int): FeatureConfig {
    fun getTrunk(): BlockState = trunk
    fun getMinHeight(): Int = minHeight
    fun getAdditionalHeight(): Int = additionalHeight

    override fun <T: Any> serialize(ops: DynamicOps<T>): Dynamic<T> {
        return Dynamic(ops, ops.createMap(
            mutableMapOf(
                "trunk".into(ops) to BlockState.serialize(ops, trunk).value,
                "min_height".into(ops) to ops.createInt(minHeight),
                "additional_height".into(ops) to ops.createInt(additionalHeight)
            )
        ))
    }

    companion object {
        fun <T> deserialize(dynamic: Dynamic<T>): DeadTreeFeatureConfig {
            val trunk = dynamic["trunk"].map { BlockState.deserialize(it) }.orElse(Blocks.OAK_LOG.defaultState)
            val minHeight = dynamic["min_height"].map {
                val value = it.value
                if (value is Int) value else 1
            }.orElse(1).asElse(1)
            val additionalHeight = dynamic["additional_height"].map {
                val value = it.value
                if (value is Int) value else 0
            }.orElse(0).asElse(0)

            return DeadTreeFeatureConfig(trunk, minHeight, additionalHeight)
        }
    }
}