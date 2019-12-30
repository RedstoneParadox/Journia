package io.github.redstoneparadox.journia.util

import com.terraformersmc.terraform.util.TerraformSaplingGenerator
import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import net.minecraft.block.sapling.SaplingGenerator
import net.minecraft.world.gen.feature.AbstractTreeFeature
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.TreeFeatureConfig
import java.util.*

class JourniaSaplingGenerator(private val featureSuplier: () -> AbstractTreeFeature<BranchedTreeFeatureConfig>, private val configSupplier: () -> BranchedTreeFeatureConfig): SaplingGenerator() {

    override fun createTreeFeature(random: Random): ConfiguredFeature<BranchedTreeFeatureConfig, *> {
        return featureSuplier().configure(configSupplier())
    }

    companion object {
        val PINE = JourniaSaplingGenerator({JourniaFeatures.PINE_TREE}, {JourniaFeatures.PINE_TREE_CONFIG})
    }
}