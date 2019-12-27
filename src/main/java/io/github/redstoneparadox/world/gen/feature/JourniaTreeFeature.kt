package io.github.redstoneparadox.world.gen.feature

import com.mojang.datafixers.Dynamic
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockBox
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.IWorld
import net.minecraft.world.ModifiableTestableWorld
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig
import net.minecraft.world.gen.feature.AbstractTreeFeature
import java.util.*
import java.util.function.Function

abstract class JourniaTreeFeature(configDeserializer: Function<Dynamic<*>, out JourniaTreeFeatureConfig>): AbstractTreeFeature<JourniaTreeFeatureConfig>(configDeserializer) {

    override fun generate(world: ModifiableTestableWorld, random: Random, pos: BlockPos, logPositions: MutableSet<BlockPos>, leavesPositions: MutableSet<BlockPos>, blockBox: BlockBox, config: JourniaTreeFeatureConfig): Boolean {
        val height = random.nextInt(config.maxHeight) + config.minHeight
        createTrunk(world, config.trunk, height, pos)
        createLeaves(world, config.leaves, pos.up(height))
        return true
    }

    fun createTrunk(world: ModifiableTestableWorld, trunk: BlockState, height: Int, base: BlockPos) {
        val current = BlockPos.Mutable(base)
        for (i in 0 until height) {
            world.setBlockState(current, trunk, 0)
            current.setOffset(Direction.UP)
        }
    }

    abstract fun createLeaves(world: ModifiableTestableWorld, leaves: BlockState, top: BlockPos);
}