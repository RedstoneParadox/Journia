package io.github.redstoneparadox.world.gen.feature

import com.mojang.datafixers.Dynamic
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.IWorld
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig
import net.minecraft.world.gen.feature.Feature
import java.util.*
import java.util.function.Function

abstract class TreeFeature(configDeserializer: Function<Dynamic<*>, out TreeFeatureConfig>): Feature<TreeFeatureConfig>(configDeserializer) {

    override fun generate(world: IWorld, generator: ChunkGenerator<out ChunkGeneratorConfig>, random: Random, pos: BlockPos, config: TreeFeatureConfig): Boolean {
        val height = random.nextInt(config.maxHeight) + config.minHeight
        createTrunk(world, config.trunk, height, pos)
        createLeaves(world, config.leaves, pos.up(height))
        return true
    }

    fun createTrunk(world: IWorld, trunk: BlockState, height: Int, base: BlockPos) {
        val current = BlockPos.Mutable(base)
        for (i in 0 until height) {
            world.setBlockState(current, trunk, 0)
            current.setOffset(Direction.UP)
        }
    }

    abstract fun createLeaves(world: IWorld, leaves: BlockState, top: BlockPos);
}