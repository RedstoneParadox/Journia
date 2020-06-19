package io.github.redstoneparadox.journia.world.gen.foliage

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.math.BlockBox
import net.minecraft.util.math.BlockPos
import net.minecraft.world.ModifiableTestableWorld
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.foliage.FoliagePlacer
import net.minecraft.world.gen.foliage.FoliagePlacerType
import java.util.*

class GroenwoodFoliagePlacer(radius: Int, randomRadius: Int, offset: Int, randomOffset: Int): FoliagePlacer(radius, randomRadius, offset, randomOffset) {

    override fun generate(world: ModifiableTestableWorld, random: Random?, config: TreeFeatureConfig, trunkHeight: Int, treeNode: TreeNode, foliageHeight: Int, radius: Int, leaves: MutableSet<BlockPos>, i: Int, blockBox: BlockBox?) {
        val top = treeNode.center

        if (treeNode is GroenwoodTreeNode && treeNode.branch) {
            for (x in -1..1) {
                for (y in -1..1) {
                    for (z in -1..1) {
                        val pos = top.add(x, y, z)
                        if (world.testBlockState(pos) { it.isAir } && !isCorner(x, y, z)) {
                            world.setBlockState(pos, config.leavesProvider.getBlockState(random, pos), 19)
                            leaves.add(pos)
                        }
                    }
                }
            }
        }
        else {
            for (x in -2..2) {
                for (y in -1..3) {
                    for (z in -2..2) {
                        val pos = top.add(x, y, z)
                        if (world.testBlockState(pos) { it.isAir } && !isEdge(x, y, z) ) {
                            world.setBlockState(pos, config.leavesProvider.getBlockState(random, pos), 19)
                            leaves.add(pos)
                        }
                    }
                }
            }
        }
    }

    private fun isEdge(xOffset: Int, yOffset: Int, zOffset: Int): Boolean {
        return if (yOffset == -1 || yOffset == 3) {
            xOffset == -2 || zOffset == -2 || xOffset == 2 || zOffset == 2
        } else if (xOffset == -2 || xOffset == 2) {
            zOffset == -2 || zOffset == 2
        } else {
            false
        }
    }

    private fun isCorner(xOffset: Int, yOffset: Int, zOffset: Int): Boolean {
        return (xOffset == -1 || xOffset == 1) && (yOffset == -1 || yOffset == 1) && (zOffset == -1 || zOffset == 1)
    }

    override fun getHeight(random: Random, trunkHeight: Int, config: TreeFeatureConfig): Int {
        return trunkHeight + 2
    }

    override fun method_28843(): FoliagePlacerType<*> {
        return JourniaFoliagePlacers.GROENWOOD_FOLIAGE_PLACER
    }

    override fun isInvalidForLeaves(random: Random?, baseHeight: Int, dx: Int, dy: Int, dz: Int, bl: Boolean): Boolean {
        return baseHeight == dz && dy == dz && dz > 0
    }

    class GroenwoodTreeNode(center: BlockPos?, foliageRadius: Int, giantTrunk: Boolean, val branch: Boolean):
        TreeNode(center, foliageRadius, giantTrunk)

    companion object {
        val CODEC: Codec<GroenwoodFoliagePlacer> = RecordCodecBuilder.create { instance ->
            return@create instance.group(
                Codec.INT.fieldOf("radius").forGetter { it.radius },
                Codec.INT.fieldOf("radius_random").forGetter { it.randomRadius },
                Codec.INT.fieldOf("offset").forGetter { it.offset },
                Codec.INT.fieldOf("offset_random").forGetter { it.randomOffset }
            ).apply(instance, ::GroenwoodFoliagePlacer)
        }
    }
}