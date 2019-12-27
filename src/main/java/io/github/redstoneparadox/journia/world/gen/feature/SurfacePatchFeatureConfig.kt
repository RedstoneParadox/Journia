package io.github.redstoneparadox.journia.world.gen.feature

import com.mojang.datafixers.Dynamic
import com.mojang.datafixers.types.DynamicOps
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.world.gen.feature.FeatureConfig
import kotlin.streams.asStream

class SurfacePatchFeatureConfig(private val state: BlockState, private val targets: List<BlockState>): FeatureConfig {
    override fun <T: Any> serialize(ops: DynamicOps<T>): Dynamic<T> {
        return Dynamic(ops, ops.createMap(
            mapOf(
                ops.createString("state") to BlockState.serialize(ops, state).value,
                ops.createString("targets") to ops.createList(targets.map { BlockState.serialize(ops, it).value }.asSequence().asStream())
            )
        ))
    }

    fun matches(ground: BlockState): Boolean {
        for (target in targets) if (target == ground) return true;
        return false
    }

    companion object {
        fun <T: Any> deserialize(dynamic: Dynamic<T>): SurfacePatchFeatureConfig {
            val state = dynamic["state"].map { BlockState.deserialize(it) }.orElse(Blocks.GRASS_BLOCK.defaultState)
            val targets = dynamic["targets"].map { dyn ->
                dyn.asListOpt { BlockState.deserialize(it) }.orElse(listOf())
            }.orElse(listOf())

            return SurfacePatchFeatureConfig(state, targets)
        }
    }
}