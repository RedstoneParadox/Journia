package io.github.redstoneparadox.journia.world.gen.feature

import com.terraformersmc.terraform.shapes.api.Filler
import com.terraformersmc.terraform.shapes.api.Position
import com.terraformersmc.terraform.shapes.api.layer.TransformationLayer
import com.terraformersmc.terraform.shapes.impl.layer.transform.TranslateLayer
import io.github.redstoneparadox.journia.block.JourniaBlocks
import io.github.redstoneparadox.journia.util.JavaRandom
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.Heightmap
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.Feature
import java.util.*

class MudIslandFeature: Feature<MudIslandFeatureConfig>(MudIslandFeatureConfig.CODEC) {
    override fun generate(world: StructureWorldAccess, chunkGenerator: ChunkGenerator, random: Random, pos: BlockPos, config: MudIslandFeatureConfig): Boolean {
        if (pos.y > chunkGenerator.seaLevel - 2) {
            return false
        }

        if (!world.testBlockState(BlockPos(pos.x, chunkGenerator.seaLevel, pos.z)) { it.block.`is`(Blocks.WATER) }) {
            return false
        }

        val filler = IslandFiller(world, chunkGenerator.seaLevel)
        val shapeProvider = config.shapeProvider
        val countRange = config.countRange
        val offsetRange = config.offsetRange
        val rand = JavaRandom(random)
        val count = rand.nextInt(countRange.first, countRange.last)

        for (i in 0..count) {
            val shape = shapeProvider.get(rand)

            val offsetDistance = rand.nextInt(offsetRange.first, offsetRange.last + 1).toDouble()
            val offsetAngle = Math.toRadians(rand.nextInt(0, 360).toDouble()).toFloat()
            val offset = Vec3d(offsetDistance, 0.0, 0.0).rotateY(offsetAngle)

            shape.applyLayer(TranslateLayer.of(Position.of(pos.add(offset.x.toInt(), 0, offset.z.toInt()))))
            shape.fill(filler)
        }

        return true
    }

    class IslandFiller(val world: StructureWorldAccess, val seaLevel: Int): Filler {
        override fun accept(pos: Position) {
            val blockPos = pos.toBlockPos()

            val chunk = world.getChunk(blockPos)
            val y = chunk.sampleHeightmap(Heightmap.Type.OCEAN_FLOOR_WG, blockPos.x, blockPos.z)

            if (y <= seaLevel) {
                val newBlockPos = BlockPos(blockPos.x, y, blockPos.z)
                world.setBlockState(newBlockPos, JourniaBlocks.MUD.defaultState, 3)
            }
        }
    }
}