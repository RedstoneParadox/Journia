package io.github.redstoneparadox.journia.world.gen.filler

import com.terraformersmc.shapes.api.Position
import com.terraformersmc.shapes.impl.filler.SimpleFiller
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.ModifiableTestableWorld
import net.minecraft.world.TestableWorld

class PredicateFiller(val world: ModifiableTestableWorld, state: BlockState, val predicate: (BlockState) -> Boolean): SimpleFiller(world, state) {
    override fun accept(position: Position) {
        if (world.testBlockState(position.toBlockPos(), predicate)) {
            super.accept(position)
        }
    }
}