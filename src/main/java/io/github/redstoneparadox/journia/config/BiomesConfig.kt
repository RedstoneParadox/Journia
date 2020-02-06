package io.github.redstoneparadox.journia.config

import redstoneparadox.paradoxconfig.config.ConfigCategory
import redstoneparadox.paradoxconfig.config.RootConfigCategory
import redstoneparadox.paradoxconfig.serialization.ConfigDeserializer
import redstoneparadox.paradoxconfig.serialization.ConfigSerializer
import redstoneparadox.paradoxconfig.serialization.jankson.JanksonConfigDeserializer
import redstoneparadox.paradoxconfig.serialization.jankson.JanksonConfigSerializer

object BiomesConfig: RootConfigCategory("biomes.json5") {
    override val deserializer: ConfigDeserializer<*> = JanksonConfigDeserializer()
    override val serializer: ConfigSerializer<*> = JanksonConfigSerializer()

    object Wasteland: ConfigCategory("wasteland") {
        val enabled: Boolean by option(true, "enabled", "Sets whether this biome is enabled.")
        val weight: Double by option(0.8, "weight", "Sets the weight for this biome.")
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

    object JungleWetlands: ConfigCategory("jungle_wetlands") {
        val enabled: Boolean by option(true, "enabled", "Sets whether the Jungle Wetlands biome is enabled.")
        val chance: Double by option(0.2, "chance", "Sets the chance for Jungle Wetlands to replace a Jungle biome.")
    }

    object Vanilla: ConfigCategory("vanilla", "Options that affect vanilla biome generation.") {
        val taigaPineTrees: Boolean by option(false, "taiga_pine_trees", "Sets whether or not pine trees should generate in Taiga biomes.")
    }
}