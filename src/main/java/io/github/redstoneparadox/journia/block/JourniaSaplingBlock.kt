package io.github.redstoneparadox.journia.block

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.SaplingBlock
import net.minecraft.block.sapling.SaplingGenerator
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import java.util.*

class JourniaSaplingBlock(private val trunk: BlockState, generator: SaplingGenerator): SaplingBlock(generator, FabricBlockSettings.copy(Blocks.OAK_SAPLING).build()) {
    private val generator2 = generator

    override fun generate(serverWorld: ServerWorld, blockPos: BlockPos, blockState: BlockState, random: Random) {
        if (blockState.get(STAGE) as Int == 0) {
            serverWorld.setBlockState(blockPos, blockState.cycle(STAGE), 4)
        } else {
            generator2.generate(serverWorld, serverWorld.chunkManager.chunkGenerator, blockPos, blockState, random)
            // TODO: Figure out what's causing the issue that requires me to do this manually.
            serverWorld.setBlockState(blockPos, trunk)
        }
    }
}