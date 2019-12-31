package io.github.redstoneparadox.journia.world.gen.feature

import com.mojang.datafixers.Dynamic
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockBox
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.ModifiableTestableWorld
import net.minecraft.world.gen.feature.AbstractTreeFeature
import net.minecraft.world.gen.feature.BranchedTreeFeature
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig
import java.util.*
import java.util.function.Function

abstract class JourniaTreeFeature(configDeserializer: Function<Dynamic<*>, out BranchedTreeFeatureConfig>): BranchedTreeFeature<BranchedTreeFeatureConfig>(configDeserializer) {

    override fun generate(world: ModifiableTestableWorld, random: Random, pos: BlockPos, logPositions: MutableSet<BlockPos>, leavesPositions: MutableSet<BlockPos>, blockBox: BlockBox, config: BranchedTreeFeatureConfig): Boolean {
        if (!isNaturalDirtOrGrass(world, pos.down())) return false
        val height = random.nextInt(config.heightRandA - config.baseHeight + 1) + config.baseHeight

        val base = findPositionToGenerate(world, height + config.foliageHeight, 0, 0, pos, config)

        if (base.isPresent) {
            val trunk = config.trunkProvider.getBlockState(random, pos)
            val leaves = config.leavesProvider.getBlockState(random, pos)

            createTrunk(world, trunk, height, pos)
            createBranches(world, trunk, height, pos)
            createLeaves(world, leaves, pos.up(height), height)
            return true
        }
        return false
    }

    // -7, 5, 22
    open fun createTrunk(world: ModifiableTestableWorld, trunk: BlockState, height: Int, base: BlockPos) {
        val current = BlockPos.Mutable(base)
        world.setBlockState(base, Blocks.AIR.defaultState, 3)
        for (i in 0 until height) {
            world.setBlockState(current, trunk, 3)
            current.setOffset(Direction.UP)
        }
    }

    open fun createBranches(world: ModifiableTestableWorld, trunk: BlockState, height: Int, top: BlockPos) {

    }

    abstract fun createLeaves(world: ModifiableTestableWorld, leaves: BlockState, top: BlockPos, height: Int)
}