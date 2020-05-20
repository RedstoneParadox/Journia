package io.github.redstoneparadox.journia.world.gen.foliage

import com.mojang.datafixers.Dynamic
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.ModifiableTestableWorld
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.foliage.FoliagePlacer
import java.util.*

class PineFoliagePlacer(radius: Int, randomRadius: Int, offset: Int, randomOffset: Int): FoliagePlacer(radius, randomRadius, offset, randomOffset, JourniaFoliagePlacers.PINE_FOLIAGE_PLACER) {

    constructor(dynamic: Dynamic<*>): this(dynamic.get("radius").asInt(0), dynamic.get("radius_random").asInt(0), dynamic.get("offset").asInt(0), dynamic.get("offset_random").asInt(0))

    override fun generate(world: ModifiableTestableWorld, random: Random, config: TreeFeatureConfig, trunkHeight: Int, treeNode: TreeNode, foliageHeight: Int, radius: Int, leaves: MutableSet<BlockPos>, i: Int) {
        val top = treeNode.center.up()

        world.setBlockState(top, config.leavesProvider.getBlockState(random, top), 19)
        world.setBlockState(top.down(), config.leavesProvider.getBlockState(random, top.down()), 19)

        for (direction in listOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)) {
            for (i in 1..8) {
                val pos = top.offset(direction).add(0, -i, 0)
                if ((i < 8 || trunkHeight > 8) && world.testBlockState(pos) {it.isAir}) world.setBlockState(pos, config.leavesProvider.getBlockState(random, pos), 19)
            }
        }

        for (direction in listOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)) {
            for (i in 4..6) {
                val pos = top.offset(direction).offset(direction).add(0, -i, 0)
                world.setBlockState(pos, config.leavesProvider.getBlockState(random, pos), 19)
            }
        }

        for (direction in listOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)) {
            for (i in 4..6) {
                val pos = top.offset(direction).add(0, -i, 0).offset(direction.rotateYClockwise())
                world.setBlockState(pos, config.leavesProvider.getBlockState(random, pos), 19)
            }
        }
    }

    override fun getHeight(random: Random, trunkHeight: Int, config: TreeFeatureConfig?): Int {
        return trunkHeight + 1
    }

    override fun isInvalidForLeaves(random: Random, baseHeight: Int, dx: Int, dy: Int, dz: Int, bl: Boolean): Boolean {
        return baseHeight == dz && dy == dz && dz > 0
    }
}