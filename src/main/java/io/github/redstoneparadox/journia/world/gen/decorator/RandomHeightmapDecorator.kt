package io.github.redstoneparadox.journia.world.gen.decorator

import net.minecraft.util.math.BlockPos
import net.minecraft.world.Heightmap
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.decorator.DecoratorContext
import java.util.*
import java.util.stream.Stream

class RandomHeightmapDecorator: Decorator<ChanceDecoratorConfig>(ChanceDecoratorConfig.CODEC) {
    override fun getPositions(context: DecoratorContext, random: Random, config: ChanceDecoratorConfig, pos: BlockPos): Stream<BlockPos> {
        if (random.nextInt(config.chance) == 0) {
            val x: Int = random.nextInt(16) + pos.x
            val z: Int = random.nextInt(16) + pos.z
            val y: Int = context.getTopY(Heightmap.Type.MOTION_BLOCKING, x, z)
            return Stream.of(BlockPos(x, y, z))
        }
        return Stream.empty()
    }
}