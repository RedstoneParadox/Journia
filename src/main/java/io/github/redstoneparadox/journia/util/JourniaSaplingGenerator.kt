package io.github.redstoneparadox.journia.util

import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import net.minecraft.block.sapling.SaplingGenerator
import net.minecraft.world.gen.feature.*
import java.util.*

class JourniaSaplingGenerator(private val featureSuplier: () -> TreeFeature, private val configSupplier: () -> TreeFeatureConfig): SaplingGenerator() {
    constructor(configSupplier: () -> TreeFeatureConfig): this({ Feature.TREE as TreeFeature }, configSupplier)

    companion object {
        val PINE = JourniaSaplingGenerator({JourniaFeatures.PINE_TREE}, {JourniaFeatures.PINE_TREE_CONFIG})
        val CUBEN = JourniaSaplingGenerator { JourniaFeatures.cubenTreeConfig() }
    }

    override fun createTreeFeature(random: Random?, bl: Boolean): ConfiguredFeature<TreeFeatureConfig, *>? {
        return featureSuplier().configure(configSupplier())
    }
}