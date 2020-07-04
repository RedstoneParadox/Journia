package io.github.redstoneparadox.journia.world.gen.filler

import com.terraformersmc.shapes.api.Filler
import com.terraformersmc.shapes.api.Position
import net.minecraft.util.math.BlockPos

class SetFiller(val set: MutableSet<BlockPos>): Filler {
    override fun accept(position: Position) {
        set.add(position.toBlockPos())
    }
}