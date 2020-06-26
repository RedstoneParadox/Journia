package io.github.redstoneparadox.journia.world.gen.foliage

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import io.github.redstoneparadox.journia.util.ListBuilder
import net.minecraft.util.math.BlockBox
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.ModifiableTestableWorld
import net.minecraft.world.gen.feature.TreeFeature
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.foliage.FoliagePlacer
import net.minecraft.world.gen.foliage.FoliagePlacerType
import java.util.*

class PineFoliagePlacer(radius: Int, randomRadius: Int, offset: Int, randomOffset: Int): FoliagePlacer(radius, randomRadius, offset, randomOffset) {
    override fun generate(world: ModifiableTestableWorld, random: Random?, config: TreeFeatureConfig, trunkHeight: Int, treeNode: TreeNode, foliageHeight: Int, radius: Int, leaves: MutableSet<BlockPos>, i: Int, blockBox: BlockBox?) {
        val top = treeNode.center.up()
        val mutable = BlockPos.Mutable()

        LEAF_POSITIONS.forEach {
            mutable.x = top.x + it.x
            mutable.y = top.y + it.y
            mutable.z = top.z + it.z

            if (TreeFeature.canReplace(world, mutable)) world.setBlockState(mutable, config.leavesProvider.getBlockState(random, mutable), 19)
            blockBox?.encompass(BlockBox(mutable, mutable))
        }
    }

    override fun getHeight(random: Random, trunkHeight: Int, config: TreeFeatureConfig?): Int {
        return trunkHeight + 1
    }

    override fun getType(): FoliagePlacerType<*> {
        return JourniaFoliagePlacers.PINE_FOLIAGE_PLACER
    }

    override fun isInvalidForLeaves(random: Random, baseHeight: Int, dx: Int, dy: Int, dz: Int, bl: Boolean): Boolean {
        return baseHeight == dz && dy == dz && dz > 0
    }

    companion object {
        val CODEC: Codec<PineFoliagePlacer> = RecordCodecBuilder.create { instance ->
            return@create instance.group(
                Codec.INT.fieldOf("radius").forGetter { it.radius },
                Codec.INT.fieldOf("radius_random").forGetter { it.randomRadius },
                Codec.INT.fieldOf("offset").forGetter { it.offset },
                Codec.INT.fieldOf("offset_random").forGetter { it.randomOffset }
            ).apply(instance, ::PineFoliagePlacer)
        }

        val LEAF_POSITIONS: List<BlockPos> = ListBuilder<BlockPos>()
            .entries(
                BlockPos(0, 0, 0),
                BlockPos(0, -1, 0)
            )
            .entriesFromFunc {
                val list = mutableListOf<BlockPos>()
                for (direction in listOf(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)) {
                    for (i in 1..8) { list.add(BlockPos(0, -i, 0).offset(direction)) }
                }
                list
            }.entriesFromFunc {
                val list = mutableListOf<BlockPos>()
                for (direction in listOf(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)) {
                    for (i in 4..6) { list.add(BlockPos(0, -i, 0).offset(direction).offset(direction)) }
                }
                list
            }.entriesFromFunc {
                val list = mutableListOf<BlockPos>()
                for (direction in listOf(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)) {
                    for (i in 4..6) { list.add(BlockPos(0, -i, 0).offset(direction).offset(direction.rotateYClockwise())) }
                }
                list
            }
            .build()
    }
}