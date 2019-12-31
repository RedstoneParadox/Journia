package io.github.redstoneparadox.journia.block

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.SaplingBlock
import net.minecraft.block.sapling.SaplingGenerator
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import java.util.*

class JourniaSaplingBlock(generator: SaplingGenerator): SaplingBlock(generator, FabricBlockSettings.copy(Blocks.OAK_SAPLING).build()) {
}