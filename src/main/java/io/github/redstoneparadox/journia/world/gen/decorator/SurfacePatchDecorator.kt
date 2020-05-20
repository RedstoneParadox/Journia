package io.github.redstoneparadox.journia.world.gen.decorator

import com.mojang.datafixers.Dynamic
import net.minecraft.util.math.BlockPos
import net.minecraft.world.Heightmap
import net.minecraft.world.WorldAccess
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig
import net.minecraft.world.gen.decorator.CountDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator
import java.util.*
import java.util.stream.IntStream
import java.util.stream.Stream
import kotlin.reflect.KFunction1

class SurfacePatchDecorator(configDeserializer: KFunction1<@ParameterName(name = "dynamic") Dynamic<*>, CountDecoratorConfig>): Decorator<CountDecoratorConfig>(configDeserializer) {
    override fun getPositions(world: WorldAccess, generator: ChunkGenerator, random: Random, config: CountDecoratorConfig, pos: BlockPos): Stream<BlockPos> {
        val i = random.nextInt(config.count)
        return IntStream.range(0, i).mapToObj {
            val j: Int = random.nextInt(16) + pos.getX()
            val k: Int = random.nextInt(16) + pos.getZ()
            val l: Int = world.getTopY(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, j, k)
            BlockPos(j, l, k)
        }
    }
}