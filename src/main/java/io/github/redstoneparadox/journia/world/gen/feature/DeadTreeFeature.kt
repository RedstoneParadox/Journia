package io.github.redstoneparadox.journia.world.gen.feature

import io.github.redstoneparadox.journia.block.JourniaBlocks
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.PillarBlock
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.WorldAccess
import net.minecraft.world.gen.StructureAccessor
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.Feature
import java.util.*

class DeadTreeFeature: Feature<DeadTreeFeatureConfig>(DeadTreeFeatureConfig.CODEC) {
    override fun generate(
        world: StructureWorldAccess,
        chunkGenerator: ChunkGenerator,
        random: Random,
        pos: BlockPos,
        config: DeadTreeFeatureConfig
    ): Boolean {
        val trunk = config.getTrunk()
        val height = random.nextInt(config.getAdditionalHeight() + 1) + config.getMinHeight()
        val ground = world.getBlockState(pos.down())

        if (ground == JourniaBlocks.CRACKED_GROUND.defaultState || ground == Blocks.COARSE_DIRT.defaultState) {
            val fallen = generateFallen(random, world, pos, trunk, height)
            if (!fallen) for (i in 0 until height) world.setBlockState(pos.up(i), trunk, 0)
        }

        return true
    }

    private fun generateFallen(random: Random, world: WorldAccess, pos: BlockPos, trunk: BlockState, height: Int): Boolean {
        if (random.nextFloat() <= 0.5) {
            val directions = listOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST).filter { direction ->
                var fallenPos = pos
                for (i in 1..(height + 1)) {
                    fallenPos = fallenPos.offset(direction)
                    if (!world.testBlockState(fallenPos) {it.isAir}) break
                    return@filter true
                }
                false
            }

            if (directions.isEmpty()) return false

            val index = random.nextInt(directions.size)
            val direction = directions[index]
            var fallenPos = pos.offset(direction)

            world.setBlockState(pos, trunk, 0)

            for (i in 1 until height) {
                fallenPos = fallenPos.offset(direction)
                val log = if (trunk.block is PillarBlock) trunk.with(Properties.AXIS, direction.axis) else trunk
                world.setBlockState(fallenPos, log, 0)
            }

            return true
        }
        return false
    }
}