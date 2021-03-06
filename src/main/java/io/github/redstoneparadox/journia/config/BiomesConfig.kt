package io.github.redstoneparadox.journia.config

import io.github.redstoneparadox.paradoxconfig.config.ConfigCategory

object BiomesConfig: ConfigCategory("biomes.json5") {
    object BandedMountains: ConfigCategory("banded_mountains") {
        val enabled: Boolean by option(true, "enabled", "Sets the Banded Mountains biome and its variants are enabled.")
        val weight: Double by option(1.0, "weight", "Sets the weight for the Banded Mountains biome.")
        val modified_chance: Double by option(0.15, 0.0..1.0, "shattered_chance", "Sets the chance for this biome to be replaced with its modified variant.")
    }

    object CubenForest: ConfigCategory("cuben_forest", "Groenwood Forest Biome option") {
        val enabled: Boolean by option(true, "enabled", "Sets whether this biome and its variants are enabled.")
        val weight: Double by option(1.0, "weight", "sets the weight for this biome.")
        val hills_chance: Double by option(0.2, 0.0..1.0, "hills_chance", "Sets the chance for this biome to be replaced with its hills variant.")
    }

    object JungleWetlands: ConfigCategory("jungle_wetlands") {
        val enabled: Boolean by option(true, "enabled", "Sets whether the Jungle Wetlands biome is enabled.")
        val chance: Double by option(0.2, "chance", "Sets the chance for Jungle Wetlands to replace a Jungle biome.")
    }

    object RockyTaiga: ConfigCategory("rocky_taiga") {
        val enabled: Boolean by option(true, "enabled", "Sets whether this biome is enabled.")
        val weight: Double by option(1.0, "weight", "Sets the weight for this biome.")
    }

    object RockyTaigaMountains: ConfigCategory("rocky_taiga_mountains") {
        val enabled: Boolean by option(true, "enabled", "Sets whether this biome is enabled.")
        val weight: Double by option(1.0, "weight", "Sets the weight for this biome.")
    }

    object ShatteredBadlandsPlateau: ConfigCategory("shattered_badlands_plateau") {
        val enabled: Boolean by option(true, "enabled", "Sets whether this biome is enabled.")
        val chance: Double by option(0.2, 0.0..1.0, "chance", "Sets the chance for this to replace a Badlands Plateau biome.")
    }

    object Vanilla: ConfigCategory("vanilla", "Options that affect vanilla biome generation.") {
        val taigaPineTrees: Boolean by option(false, "taiga_pine_trees", "Sets whether or not pine trees should generate in Taiga biomes.")
    }

    object Wasteland: ConfigCategory("wasteland") {
        val enabled: Boolean by option(true, "enabled", "Sets whether this biome is enabled.")
        val weight: Double by option(0.8, "weight", "Sets the weight for this biome.")
    }
}