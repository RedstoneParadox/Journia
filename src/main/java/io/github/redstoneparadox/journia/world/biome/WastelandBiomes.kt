package io.github.redstoneparadox.journia.world.biome

import com.terraformersmc.terraform.biome.builder.DefaultFeature.*
import com.terraformersmc.terraform.biome.builder.TerraformBiome
import io.github.redstoneparadox.journia.colorToInt
import io.github.redstoneparadox.journia.util.skyFogColor
import io.github.redstoneparadox.journia.world.gen.surfacebuilder.JourniaSurfaceBuilders
import net.minecraft.entity.EntityType
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.feature.StrongholdFeature
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

object WastelandBiomes {

    val WASTELAND: Biome

    init {
        val template = TerraformBiome.Template(TerraformBiome.builder()
            .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, JourniaSurfaceBuilders.WASTELAND_CONFIG)
            .precipitation(Biome.Precipitation.NONE)
            .category(Biome.Category.DESERT)
            .depth(0.125F).scale(0.05F)
            .skyFogColor(colorToInt(0.8, 0.8, 0.6))
            .foliageColor(10387789)
            .grassColor(9470285)
            .waterColor(6388580)
            .waterFogColor(2302743)
            .addStructureFeatures(
                StrongholdFeature.STRONGHOLD.configure(FeatureConfig.DEFAULT)
            )
            .addDefaultMonsterSpawnEntries()
            .addSpawnEntry(Biome.SpawnEntry(EntityType.HUSK, 200, 4, 6))
            .addDefaultFeatures(
                LAND_CARVERS,
                DUNGEONS,
                MINEABLES,
                ORES,
                DISKS,
                DEFAULT_MUSHROOMS,
                BADLANDS_GRASS,
                FOSSILS
            )
        )

        WASTELAND = template.builder()
            .build()
    }
}