package io.github.redstoneparadox.journia.world.gen.feature

import com.mojang.datafixers.Dynamic
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.tag.BlockTags
import net.minecraft.util.math.BlockBox
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.ModifiableTestableWorld
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.TestableWorld
import net.minecraft.world.gen.StructureAccessor
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.TreeFeature
import net.minecraft.world.gen.feature.TreeFeatureConfig
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
import java.util.*
import java.util.function.Function

abstract class JourniaTreeFeature(configDeserializer: Function<Dynamic<*>, out TreeFeatureConfig>): TreeFeature(configDeserializer) {

    open fun gen(world: ServerWorldAccess, structureAccessor: StructureAccessor, chunkGenerator: ChunkGenerator, random: Random, pos: BlockPos, config: TreeFeatureConfig): Boolean {
        if (!world.testBlockState(pos.down(), Companion::isDirtOrGrass)) return false
        val height = config.trunkPlacer.getHeight(random)

        if (hasSpace(world, config.foliagePlacer.getHeight(random, height, config), 2, pos)) {
            val trunk = config.trunkProvider.getBlockState(random, pos)
            val leaves = config.leavesProvider.getBlockState(random, pos)

            createTrunk(world, trunk, height, pos)
            createBranches(world, trunk, height, pos)
            createLeaves(world, leaves, pos.up(height), height)
            return true
        }
        return false
    }

    private fun hasSpace(world: TestableWorld, height: Int, radius: Int, base: BlockPos): Boolean {
        for (x in -radius..radius) {
            for (y in 0 until height) {
                for (z in -radius..radius) {
                    val current = base.add(x, y, z)
                    if (!world.testBlockState(current, Companion::isReplaceable)) return false
                }
            }
        }
        return true
    }

    // -7, 5, 22
    open fun createTrunk(world: ModifiableTestableWorld, trunk: BlockState, height: Int, base: BlockPos) {
        val current = BlockPos.Mutable(base.x, base.y, base.z)
        world.setBlockState(base, Blocks.AIR.defaultState, 3)
        for (i in 0 until height) {
            world.setBlockState(current, trunk, 3)
            current.offset(Direction.UP)
        }
    }

    open fun createBranches(world: ModifiableTestableWorld, trunk: BlockState, height: Int, top: BlockPos) {

    }

    abstract fun createLeaves(world: ModifiableTestableWorld, leaves: BlockState, top: BlockPos, height: Int)

    companion object {
        fun isDirtOrGrass(state: BlockState): Boolean {
            val block = state.block
            return block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.GRASS_BLOCK
        }

        fun isReplaceable(state: BlockState): Boolean {
            val block = state.block
            return block == Blocks.AIR || block.isIn(BlockTags.LEAVES) || block.isIn(BlockTags.LOGS) || block.isIn(BlockTags.SAPLINGS) || block == Blocks.VINE
        }
    }
}