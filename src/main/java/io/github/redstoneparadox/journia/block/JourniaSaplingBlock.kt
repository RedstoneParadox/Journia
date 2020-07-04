package io.github.redstoneparadox.journia.block

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Blocks
import net.minecraft.block.SaplingBlock
import net.minecraft.block.sapling.SaplingGenerator

class JourniaSaplingBlock(generator: SaplingGenerator): SaplingBlock(generator, FabricBlockSettings.copy(Blocks.OAK_SAPLING)) {
}