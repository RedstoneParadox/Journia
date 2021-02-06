package io.github.redstoneparadox.journia.block

import com.terraformersmc.terraform.tree.block.TerraformSaplingBlock
import com.terraformersmc.terraform.wood.block.StrippableLogBlock
import com.terraformersmc.terraform.wood.block.TerraformButtonBlock
import com.terraformersmc.terraform.wood.block.TerraformPressurePlateBlock
import com.terraformersmc.terraform.wood.block.TerraformStairsBlock
import io.github.redstoneparadox.journia.util.JourniaSaplingGenerator
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.registry.Registry
import java.util.function.Supplier

object JourniaBlocks {
    val CRACKED_GROUND = Block(FabricBlockSettings.copy(Blocks.SANDSTONE))

    val GREEN_MUSHROOM_BLOCK = GreenMushroomBlock()

    val PINE_LOG = StrippableLogBlock({ STRIPPED_PINE_LOG }, MaterialColor.RED, FabricBlockSettings.copy(Blocks.OAK_LOG))
    val STRIPPED_PINE_LOG = PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_LOG))
    val PINE_WOOD = StrippableLogBlock({ STRIPPED_PINE_WOOD }, MaterialColor.RED, FabricBlockSettings.copy(Blocks.OAK_WOOD))
    val STRIPPED_PINE_WOOD = PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_WOOD))
    val PINE_LEAVES = LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES))
    val PINE_SAPLING = TerraformSaplingBlock(JourniaSaplingGenerator.PINE)
    val PINE_PLANKS = Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS))
    val PINE_SLAB = SlabBlock(FabricBlockSettings.copy(Blocks.OAK_SLAB))
    val PINE_STAIRS = TerraformStairsBlock(PINE_PLANKS, FabricBlockSettings.copy(Blocks.OAK_STAIRS))
    val PINE_FENCE = FenceBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE))
    val PINE_FENCE_GATE = FenceGateBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE_GATE))
    val PINE_BUTTON = TerraformButtonBlock(FabricBlockSettings.copy(Blocks.OAK_BUTTON))
    val PINE_PRESSURE_PLATE = TerraformPressurePlateBlock(FabricBlockSettings.copy(Blocks.OAK_PRESSURE_PLATE))

    val MUD = Block(FabricBlockSettings.of(Material.ORGANIC_PRODUCT).materialColor(MaterialColor.DIRT).slipperiness(0.8F).sounds(BlockSoundGroup.SLIME))

    fun init() {
        register("cracked_ground", CRACKED_GROUND)

        // register("green_mushroom_block", GREEN_MUSHROOM_BLOCK)

        register("pine_log", PINE_LOG)
        register("stripped_pine_log", STRIPPED_PINE_LOG)
        register("pine_wood", PINE_WOOD)
        register("stripped_pine_wood", STRIPPED_PINE_WOOD)
        register("pine_leaves", PINE_LEAVES)
        register("pine_sapling", PINE_SAPLING)
        register("pine_planks", PINE_PLANKS)
        register("pine_slab", PINE_SLAB)
        register("pine_stairs", PINE_STAIRS)
        register("pine_fence", PINE_FENCE)
        register("pine_fence_gate", PINE_FENCE_GATE)
        register("pine_button", PINE_BUTTON)
        register("pine_pressure_plate", PINE_PRESSURE_PLATE)

        register("mud", MUD)
    }

    private fun register(id: String, block: Block) {
        Registry.register(Registry.BLOCK, "journia:$id", block)
    }
}