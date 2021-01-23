package io.github.redstoneparadox.journia.world.gen.filler

import com.terraformersmc.terraform.shapes.api.Filler
import com.terraformersmc.terraform.shapes.api.Position
import net.minecraft.block.BlockState
import net.minecraft.world.ModifiableTestableWorld

class PredicateFiller(val world: ModifiableTestableWorld, val state: BlockState, private val predicate: (BlockState) -> Boolean): Filler {
    override fun accept(position: Position) {
        val pos = position.toBlockPos()
        if (world.testBlockState(pos, predicate)) {
            world.setBlockState(pos, state, 19)
        }
    }
}