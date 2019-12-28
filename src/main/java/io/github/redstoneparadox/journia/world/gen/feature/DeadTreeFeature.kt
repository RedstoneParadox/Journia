package io.github.redstoneparadox.journia.world.gen.feature

import com.mojang.datafixers.Dynamic
import io.github.redstoneparadox.journia.block.JourniaBlocks
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig
import net.minecraft.world.gen.feature.Feature
import java.util.*
import java.util.function.Function

class DeadTreeFeature(configDeserializer: Function<Dynamic<*>, out DeadTreeFeatureConfig>): Feature<DeadTreeFeatureConfig>(configDeserializer) {
    override fun generate(world: IWorld, generator: ChunkGenerator<out ChunkGeneratorConfig>, random: Random, pos: BlockPos, config: DeadTreeFeatureConfig): Boolean {
        val trunk = config.getTrunk()
        val height = random.nextInt(config.getAdditionalHeight() + 1) + config.getMinHeight()
        val ground = world.getBlockState(pos.down())

        if (ground == JourniaBlocks.CRACKED_GROUND.defaultState || ground == Blocks.COARSE_DIRT.defaultState) {
            for (i in 0 until height) world.setBlockState(pos.up(i), trunk, 0)
        }

        return true
    }
}