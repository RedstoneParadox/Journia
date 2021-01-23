package io.github.redstoneparadox.journia.world.gen.filler

import com.terraformersmc.terraform.shapes.api.Filler
import com.terraformersmc.terraform.shapes.api.Position
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos

class SetFiller(val set: MutableSet<Pair<BlockPos, BlockState>>, private val stateProvider: (BlockPos) -> BlockState): Filler {
    override fun accept(position: Position) {
        val blockPos = position.toBlockPos()
        set.add(blockPos to stateProvider(blockPos))
    }
}