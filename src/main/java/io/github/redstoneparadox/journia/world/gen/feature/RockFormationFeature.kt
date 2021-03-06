package io.github.redstoneparadox.journia.world.gen.feature

import com.terraformersmc.terraform.shapes.api.Position
import com.terraformersmc.terraform.shapes.impl.layer.pathfinder.AddLayer
import com.terraformersmc.terraform.shapes.impl.layer.transform.TranslateLayer
import io.github.redstoneparadox.journia.util.JavaRandom
import io.github.redstoneparadox.journia.world.gen.filler.CachingFiller
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.Feature
import java.util.*

class RockFormationFeature: Feature<RockFormationFeatureConfig>(RockFormationFeatureConfig.CODEC) {
    override fun generate(
        world: StructureWorldAccess,
        chunkGenerator: ChunkGenerator,
        random: Random,
        pos: BlockPos,
        config: RockFormationFeatureConfig
    ): Boolean {
        val rand = JavaRandom(random)
        val additionalRockCount = rand.nextInt(config.countRange.first, config.countRange.last + 1) - 1

        var shape = config.shapeProvider.get(rand)

        for (i in 0 until additionalRockCount) {
            val offsetDistance = rand.nextInt(config.offsetRange.first, config.offsetRange.last + 1).toDouble()
            val offsetAngle = Math.toRadians(rand.nextInt(0, 360).toDouble()).toFloat()
            val offset = Vec3d(offsetDistance, 0.0, 0.0).rotateY(offsetAngle)

            val rock = config.shapeProvider.get(rand)
                .applyLayer(TranslateLayer.of(Position.of(offset.x, 0.0, offset.z)))

            shape = shape.applyLayer(AddLayer(rock))
        }

        val filler = CachingFiller(world, Blocks.STONE.defaultState)
        shape.applyLayer(TranslateLayer.of(Position.of(pos.down(3)))).stream().forEach(filler)

        val mutable = BlockPos.Mutable()
        for (x in (filler.min.x..filler.max.x)) {
            for (z in filler.min.z..filler.max.z) {
                for (y in filler.min.y..filler.max.y) {
                    mutable.set(x, y, z)
                    if (world.getBlockState(mutable).isAir) {
                        mutable.move(Direction.DOWN)
                        if (rand.nextFloat() <= 0.6 && world.getBlockState(mutable) == Blocks.STONE.defaultState) {
                            world.setBlockState(mutable, Blocks.AIR.defaultState, 19)
                            break
                        }
                    }
                }
            }
        }

        for (x in (filler.min.x..filler.max.x)) {
            for (z in filler.min.z..filler.max.z) {
                mutable.set(x, filler.min.y, z)
                if (world.getBlockState(mutable) == Blocks.STONE.defaultState) {
                    for (y in (filler.min.y - 1) downTo 0) {
                        mutable.set(x, y, z)
                        if (world.testBlockState(mutable) { it.isAir || it == Blocks.GRASS_BLOCK.defaultState || it == Blocks.DIRT.defaultState }) {
                            world.setBlockState(mutable, Blocks.STONE.defaultState, 19)
                        }
                        else {
                            break
                        }
                    }
                }
            }
        }

        return true
    }
}