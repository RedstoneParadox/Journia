package io.github.redstoneparadox.journia.world.gen.filler

import com.terraformersmc.terraform.shapes.api.Position
import com.terraformersmc.terraform.shapes.impl.filler.SimpleFiller
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.ModifiableWorld
import kotlin.math.max
import kotlin.math.min

class CachingFiller(world: ModifiableWorld, state: BlockState): SimpleFiller(world, state) {
    val min: BlockPos.Mutable = BlockPos.Mutable()
    val max: BlockPos.Mutable = BlockPos.Mutable()
    private var initialized: Boolean = false

    override fun accept(pos: Position) {
        super.accept(pos)

        if (initialized) {
            min.set(min(pos.x, min.x.toDouble()), min(pos.y, min.y.toDouble()), min(pos.z, min.z.toDouble()))
            max.set(max(pos.x, max.x.toDouble()), max(pos.y, max.y.toDouble()), max(pos.z, max.z.toDouble()))
        } else {
            min.set(pos.x, pos.y, pos.z)
            max.set(pos.x, pos.y, pos.z)
            initialized = true
        }
    }
}