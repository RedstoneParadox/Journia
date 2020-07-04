package io.github.redstoneparadox.journia.block

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.minecraft.block.Blocks
import net.minecraft.block.SaplingBlock
import net.minecraft.block.sapling.SaplingGenerator

class JourniaSaplingBlock(generator: SaplingGenerator): SaplingBlock(generator, FabricBlockSettings.copy(Blocks.OAK_SAPLING).build()) {
}