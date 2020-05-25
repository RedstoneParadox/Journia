package io.github.redstoneparadox.journia.world.gen.foliage

import com.mojang.datafixers.Dynamic
import net.minecraft.util.math.BlockPos
import net.minecraft.world.ModifiableTestableWorld
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.foliage.FoliagePlacer
import net.minecraft.world.gen.foliage.FoliagePlacerType
import java.util.*

class GroenwoodFoliagePlacer(radius: Int, randomRadius: Int, offset: Int, randomOffset: Int) : FoliagePlacer(radius, randomRadius, offset, randomOffset, JourniaFoliagePlacers.GROENWOOD_FOLIAGE_PLACER) {
    constructor(dynamic: Dynamic<*>): this(dynamic.get("radius").asInt(0), dynamic.get("radius_random").asInt(0), dynamic.get("offset").asInt(0), dynamic.get("offset_random").asInt(0))

    override fun generate(world: ModifiableTestableWorld, random: Random, config: TreeFeatureConfig, trunkHeight: Int, treeNode: TreeNode, foliageHeight: Int, radius: Int, leaves: MutableSet<BlockPos>, i: Int) {
        val top = treeNode.center

        for (x in -2..2) {
            for (y in -2..2) {
                for (z in -2..2) {
                    val pos = top.add(x, y, z)
                    world.setBlockState(pos, config.leavesProvider.getBlockState(random, pos), 19)
                    leaves.add(pos)
                }
            }
        }
    }

    override fun getHeight(random: Random, trunkHeight: Int, config: TreeFeatureConfig): Int {
        return trunkHeight + 2
    }

    override fun isInvalidForLeaves(random: Random?, baseHeight: Int, dx: Int, dy: Int, dz: Int, bl: Boolean): Boolean {
        return baseHeight == dz && dy == dz && dz > 0
    }
}