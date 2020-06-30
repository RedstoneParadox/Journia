package io.github.redstoneparadox.journia.world.gen.util

import com.terraformersmc.shapes.api.Filler
import com.terraformersmc.shapes.api.Position
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.ModifiableWorld
import kotlin.math.max
import kotlin.math.min

class BlockStateView {
    val min: BlockPos.Mutable = BlockPos.Mutable()
    val max: BlockPos.Mutable = BlockPos.Mutable()
    val states: MutableMap<BlockPos, BlockState> = mutableMapOf()

    fun setBlockState(pos: BlockPos, state: BlockState) {
        states[pos] = state

        min.x = min(pos.x, min.x)
        min.y = min(pos.y, min.y)
        min.z = min(pos.z, min.z)

        max.x = max(pos.x, max.x)
        max.y = max(pos.y, max.y)
        max.z = max(pos.z, max.z)
    }

    inline fun forX(func: BlockStateView.(x: Int) -> Unit) {
        for (x in min.x..max.x) this.func(x)
    }

    inline fun forY(func: BlockStateView.(y: Int) -> Unit) {
        for (y in min.y..max.y) this.func(y)
    }

    inline fun forZ(func: BlockStateView.(z: Int) -> Unit) {
        for (z in min.z..max.z) this.func(z)
    }

    inline fun forAll(func: BlockStateView.(BlockPos.Mutable) -> Unit) {
        val pos = BlockPos.Mutable()
        forX { x ->
            forY { y ->
                forZ { z ->
                    pos.set(x, y, z)
                    func(pos)
                }
            }
        }
    }

    inline fun forTop(func: BlockStateView.(BlockPos.Mutable) -> Unit) {
        val pos = BlockPos.Mutable()
        forX { x ->
            forZ { z ->
                forY { y ->
                    pos.set(x, y, z)
                    val state = states[pos]
                    if (state == null || state == Blocks.AIR.defaultState) {
                        pos.y -= 1
                        func(pos)
                        return@forZ
                    }
                }
            }
        }
    }

    fun filler(state: BlockState): Filler {
        return object: Filler {
            val view = this@BlockStateView

            override fun accept(position: Position) {
                view.setBlockState(position.toBlockPos(), state)
            }
        }
    }

    fun addToWorld(world: ModifiableWorld) {
        forAll {
            val state = states[it]
            try {
                if (state != null) world.setBlockState(it, state, 19)
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}