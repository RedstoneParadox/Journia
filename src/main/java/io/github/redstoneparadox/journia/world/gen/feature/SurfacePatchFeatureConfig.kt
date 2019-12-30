package io.github.redstoneparadox.journia.world.gen.feature

import com.mojang.datafixers.Dynamic
import com.mojang.datafixers.types.DynamicOps
import io.github.redstoneparadox.journia.into
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.world.gen.feature.FeatureConfig

class SurfacePatchFeatureConfig(val state: BlockState, val startRadius: Int, val target: Target, val integrity: Double): FeatureConfig {
    override fun <T : Any> serialize(ops: DynamicOps<T>): Dynamic<T> {
        return Dynamic(ops, ops.createMap(
            mutableMapOf(
                "state".into(ops) to BlockState.serialize(ops, state).value,
                "start_radius".into(ops) to ops.createInt(startRadius),
                "target".into(ops) to target.name.into(ops),
                "integrity".into(ops) to ops.createDouble(integrity)
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
            val target = dynamic["target"].map {
                if (it.value is String) Target.valueOf(it.value as String) else Target.GRASS
            }.orElse(Target.GRASS)
            val integrity = dynamic["integrity"].map {
                val value = it.value
                if (value is Double) value as Double else 1.0
            }.orElse(1.0)

            return SurfacePatchFeatureConfig(state, startRadius, target, integrity)
        }
    }

    enum class Target(private val predicate: (BlockState) -> Boolean) {
        GRASS({ it.block == Blocks.GRASS_BLOCK }),
        STONE({it.block == Blocks.STONE || it.block == Blocks.ANDESITE});

        fun test(state: BlockState) = this.predicate(state)
    }
}