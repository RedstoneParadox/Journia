package io.github.redstoneparadox.journia.util

import io.github.redstoneparadox.journia.world.gen.feature.JourniaFeatures
import net.minecraft.block.sapling.SaplingGenerator
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.TreeFeatureConfig
import java.util.*

class JourniaSaplingGenerator(private val featureSuplier: () -> Feature<TreeFeatureConfig>, private val configSupplier: () -> TreeFeatureConfig): SaplingGenerator() {
    constructor(configSupplier: () -> TreeFeatureConfig): this({ Feature.TREE }, configSupplier)

    companion object {
        val PINE = JourniaSaplingGenerator { JourniaFeatures.PINE_TREE_CONFIG }
    }

    override fun createTreeFeature(random: Random?, bl: Boolean): ConfiguredFeature<TreeFeatureConfig, *>? {
        return featureSuplier().configure(configSupplier())
    }
}