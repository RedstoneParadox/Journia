package io.github.redstoneparadox.journia.world.gen.filler

import com.terraformersmc.shapes.api.Filler
import com.terraformersmc.shapes.api.Position
import com.terraformersmc.shapes.impl.filler.SimpleFiller
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.ModifiableTestableWorld
import net.minecraft.world.TestableWorld

class PredicateFiller(val world: ModifiableTestableWorld, val state: BlockState, private val predicate: (BlockState) -> Boolean): Filler {
    override fun accept(position: Position) {
        val pos = position.toBlockPos()
        if (world.testBlockState(pos, predicate)) {
            world.setBlockState(pos, state, 3, 64)
        }
    }
}