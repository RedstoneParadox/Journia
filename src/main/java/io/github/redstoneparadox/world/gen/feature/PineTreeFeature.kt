package io.github.redstoneparadox.world.gen.feature

import com.mojang.datafixers.Dynamic
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import net.minecraft.world.ModifiableTestableWorld
import java.util.function.Function

class PineTreeFeature(configDeserializer: Function<Dynamic<*>, out JourniaTreeFeatureConfig>): JourniaTreeFeature(configDeserializer) {
    override fun createLeaves(world: ModifiableTestableWorld, leaves: BlockState, top: BlockPos) {
        world.setBlockState(top, leaves, 0)
    }
}