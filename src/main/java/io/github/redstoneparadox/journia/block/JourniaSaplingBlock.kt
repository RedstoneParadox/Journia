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

    override fun generate(serverWorld: ServerWorld, blockPos: BlockPos, blockState: BlockState, random: Random) {
        super.generate(serverWorld, blockPos, blockState, random)
        // TODO: Figure out why the sapling generator isn't replacing the sapling so that I can remove this.
        if (blockState.get(STAGE) as Int == 1) serverWorld.setBlockState(blockPos, trunk)
    }
}