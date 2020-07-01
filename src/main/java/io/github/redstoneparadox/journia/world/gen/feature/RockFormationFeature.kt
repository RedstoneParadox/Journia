package io.github.redstoneparadox.journia.world.gen.feature

import com.terraformersmc.shapes.api.Position
import com.terraformersmc.shapes.impl.Shapes
import com.terraformersmc.shapes.impl.filler.SimpleFiller
import com.terraformersmc.shapes.impl.layer.pathfinder.AddLayer
import com.terraformersmc.shapes.impl.layer.transform.TranslateLayer
import io.github.redstoneparadox.journia.util.JavaRandom
import io.github.redstoneparadox.journia.world.gen.filler.CachingFiller
import io.github.redstoneparadox.journia.world.gen.util.BlockStateView
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.world.Heightmap
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.gen.StructureAccessor
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.Feature
import java.util.*

class RockFormationFeature: Feature<RockFormationFeatureConfig>(RockFormationFeatureConfig.CODEC) {
    override fun generate(world: ServerWorldAccess, structureAccessor: StructureAccessor, generator: ChunkGenerator, random: Random, pos: BlockPos, config: RockFormationFeatureConfig): Boolean {
        val rand = JavaRandom(random)
        val additionalRockCount = rand.nextInt(config.countRange.first, config.countRange.last + 1) - 1

        val firstRadius = rand.nextInt(config.radiusRange.first, config.radiusRange.last + 1).toDouble()
        val firstHeight = rand.nextInt(config.heightRange.first, config.heightRange.last + 1).toDouble()
        var shape = Shapes.hemiEllipsoid(firstRadius, firstRadius, firstHeight)

        for (i in 0 until additionalRockCount) {
            val radius = rand.nextInt(config.radiusRange.first, config.radiusRange.last + 1).toDouble()
            val height = rand.nextInt(config.heightRange.first, config.heightRange.last + 1).toDouble()
            val offsetDistance = rand.nextInt(config.offsetRange.first, config.offsetRange.last + 1).toDouble()
            val offsetAngle = Math.toRadians(rand.nextInt(0, 360).toDouble()).toFloat()
            val offset = Vec3d(offsetDistance, 0.0, 0.0).rotateY(offsetAngle)

            val rock = Shapes.hemiEllipsoid(radius, radius, height)
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

        return true
    }
}