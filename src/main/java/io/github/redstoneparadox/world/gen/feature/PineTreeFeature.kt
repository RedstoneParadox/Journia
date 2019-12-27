package io.github.redstoneparadox.world.gen.feature

import com.mojang.datafixers.Dynamic
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.IWorld
import net.minecraft.world.ModifiableTestableWorld
import java.util.function.Function

class PineTreeFeature(configDeserializer: Function<Dynamic<*>, out JourniaTreeFeatureConfig>): JourniaTreeFeature(configDeserializer) {
    override fun createLeaves(world: ModifiableTestableWorld, leaves: BlockState, top: BlockPos) {
        world.setBlockState(top, leaves, 0)

        for (direction in listOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)) {
            for (i in 1..8) {
                val pos = top.offset(direction).add(0, -i, 0)
                world.setBlockState(pos, leaves, 0)
            }
        }

        for (direction in listOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)) {
            for (i in 4..6) {
                val pos = top.offset(direction).offset(direction).add(0, -i, 0)
                world.setBlockState(pos, leaves, 0)
            }
        }

        for (direction in listOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)) {
            for (i in 4..6) {
                val pos = top.offset(direction).add(0, -i, 0).offset(direction.rotateYClockwise())
                world.setBlockState(pos, leaves, 0)
            }
        }
    }
}