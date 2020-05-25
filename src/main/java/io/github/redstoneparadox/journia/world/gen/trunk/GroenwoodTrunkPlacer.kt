package io.github.redstoneparadox.journia.world.gen.trunk

import com.mojang.datafixers.Dynamic
import io.github.redstoneparadox.journia.util.concat
import io.github.redstoneparadox.journia.util.then
import io.github.redstoneparadox.journia.world.gen.foliage.GroenwoodFoliagePlacer
import io.github.redstoneparadox.journia.util.wrap
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockBox
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.ModifiableTestableWorld
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.foliage.FoliagePlacer
import net.minecraft.world.gen.trunk.TrunkPlacer
import java.util.*
import kotlin.collections.HashSet
import kotlin.math.ceil
import kotlin.math.pow
import kotlin.math.roundToInt

class GroenwoodTrunkPlacer(baseHeight: Int, firstRandomHeight: Int, secondRandomHeight: Int): TrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight, JourniaTrunkPlacers.GROENWOOD_TRUNK_PLACER) {

    constructor(dynamic: Dynamic<*>): this(dynamic.get("base_height").asInt(0), dynamic.get("height_rand_a").asInt(0), dynamic.get("height_rand_b").asInt(0))

    override fun generate(world: ModifiableTestableWorld, random: Random, trunkHeight: Int, pos: BlockPos, set: MutableSet<BlockPos>, blockBox: BlockBox, config: TreeFeatureConfig): MutableList<FoliagePlacer.TreeNode> {
        for (y in 0..trunkHeight) {
            val pos = pos.up(y)
            world.setBlockState(pos, config.trunkProvider.getBlockState(random, pos), 3)
            set.add(pos)
        }

        val nodes = createBranches(world, pos, random, config, trunkHeight, set)
        blockBox.encompass(BlockBox(pos, pos.up(trunkHeight - 1)))
        return nodes.concat(mutableListOf(GroenwoodFoliagePlacer.GroenwoodTreeNode(pos.up(trunkHeight - 1), 5, false, false)))
    }

    private fun createBranches(world: ModifiableTestableWorld, pos: BlockPos, rand: Random, config: TreeFeatureConfig, trunkHeight: Int, logs: MutableSet<BlockPos>): MutableList<FoliagePlacer.TreeNode> {
        val nodes = mutableListOf<FoliagePlacer.TreeNode>()
        if (trunkHeight < 9) return nodes

        var bound = ceil(-(trunkHeight-9).toDouble().pow(2)/9 + 4).roundToInt()
        val heightSet = HashSet<Int>().then {
            if (trunkHeight == 9) {
                it.add(3)
                return@then
            }
            for (i in 3..(trunkHeight - 6)) {
                it.add(i)
            }
            return@then
        }

        for (direction in listOf(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST).shuffled(rand)) {
            if (bound <= 0 || rand.nextInt(bound) == 0) {
                val height: Int = heightSet.random(rand.wrap())
                heightSet.remove(height)
                heightSet.remove(height + 1)
                heightSet.remove(height - 1)

                bound += 1

                val pos = pos.up(height)
                for (distance in 1..3) {
                    val pos = pos.offset(direction, distance)
                    world.setBlockState(pos, config.trunkProvider.getBlockState(rand, pos).with(Properties.AXIS, direction.axis), 19)
                }
                nodes.add(
                    GroenwoodFoliagePlacer.GroenwoodTreeNode(
                        center = pos.offset(direction, 3),
                        foliageRadius = 2,
                        giantTrunk = false,
                        branch = true
                    )
                )
            }
            if (heightSet.isEmpty()) break
        }

        return nodes
    }
}