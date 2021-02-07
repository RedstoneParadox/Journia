package io.github.redstoneparadox.journia.world.gen.decorator

import net.minecraft.util.math.BlockPos
import net.minecraft.world.Heightmap
import net.minecraft.world.gen.CountConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.decorator.DecoratorContext
import java.util.*
import java.util.stream.IntStream
import java.util.stream.Stream

class SurfacePatchDecorator: Decorator<LargeCountConfig>(LargeCountConfig.CODEC) {
    override fun getPositions(context: DecoratorContext, random: Random, config: LargeCountConfig, pos: BlockPos): Stream<BlockPos> {
        val i = config.getCount().getValue(random)
        return IntStream.range(0, i).mapToObj {
            val j: Int = random.nextInt(16) + pos.getX()
            val k: Int = random.nextInt(16) + pos.getZ()
            val l: Int = context.getTopY(Heightmap.Type.OCEAN_FLOOR_WG, j, k)
            BlockPos(j, l, k)
        }
    }
}