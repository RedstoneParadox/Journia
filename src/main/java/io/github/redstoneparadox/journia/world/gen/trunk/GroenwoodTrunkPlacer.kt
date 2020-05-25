package io.github.redstoneparadox.journia.world.gen.trunk

import com.mojang.datafixers.Dynamic
import net.minecraft.util.math.BlockBox
import net.minecraft.util.math.BlockPos
import net.minecraft.world.ModifiableTestableWorld
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.foliage.FoliagePlacer
import net.minecraft.world.gen.trunk.TrunkPlacer
import java.util.*

class GroenwoodTrunkPlacer(baseHeight: Int, firstRandomHeight: Int, secondRandomHeight: Int): TrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight, JourniaTrunkPlacers.GROENWOOD_TRUNK_PLACER) {

    constructor(dynamic: Dynamic<*>): this(dynamic.get("base_height").asInt(0), dynamic.get("height_rand_a").asInt(0), dynamic.get("height_rand_b").asInt(0))

    override fun generate(world: ModifiableTestableWorld, random: Random, trunkHeight: Int, pos: BlockPos, set: MutableSet<BlockPos>, blockBox: BlockBox, config: TreeFeatureConfig): MutableList<FoliagePlacer.TreeNode> {
        for (y in 0..trunkHeight) {
            val pos = pos.up(y)
            world.setBlockState(pos, config.trunkProvider.getBlockState(random, pos), 3)
            set.add(pos)
        }

        blockBox.encompass(BlockBox(pos, pos.up(trunkHeight - 1)))
        return mutableListOf(FoliagePlacer.TreeNode(pos.up(trunkHeight - 1), 5, false))
    }
}