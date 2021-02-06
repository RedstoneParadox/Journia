package io.github.redstoneparadox.journia.world.biome

import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.biome.Biome

object JourniaBiomes {
    lateinit var GRAVEL_BEACH: RegistryKey<Biome>
    lateinit var GRASSY_BEACH: RegistryKey<Biome>

    lateinit var BANDED_MOUNTAINS: RegistryKey<Biome>
    lateinit var MODIFIED_BANDED_MOUNTAINS: RegistryKey<Biome>
    lateinit var BANDED_MOUNTAINS_RIVER: RegistryKey<Biome>
    lateinit var BANDED_SHORE: RegistryKey<Biome>

    lateinit var CUBEN_FOREST: RegistryKey<Biome>
    lateinit var CUBEN_FOREST_HILLS: RegistryKey<Biome>

    lateinit var ROCKY_TAIGA: RegistryKey<Biome>
    lateinit var ROCKY_TAIGA_HILLS: RegistryKey<Biome>
    lateinit var MODIFIED_ROCKY_TAIGA: RegistryKey<Biome>
    lateinit var ROCKY_GIANT_TREE_TAIGA: RegistryKey<Biome>
    lateinit var ROCKY_TAIGA_MOUNTAINS: RegistryKey<Biome>

    lateinit var WASTELAND: RegistryKey<Biome>
    lateinit var WASTELAND_EDGE: RegistryKey<Biome>
    lateinit var WASTELAND_RIVER: RegistryKey<Biome>
    lateinit var WASTELAND_SHORE: RegistryKey<Biome>

    lateinit var WETLANDS: RegistryKey<Biome>

    fun init() {
        ShoreBiomes.init()
        RockyTaigaBiomes.init()
        BandedMountainsBiomes.init()
        // WastelandBiomes.init()
        WetlandsBiomes.init()
    }

    fun register(name: String, biome: Biome): RegistryKey<Biome> {
        val id = Identifier("journia:$name")

        BuiltinRegistries.add(BuiltinRegistries.BIOME, id, biome)

        return RegistryKey.of(Registry.BIOME_KEY, id)
    }

    fun getSkyColor(temperature: Float): Int {
        var f = temperature / 3.0f
        f = MathHelper.clamp(f, -1.0f, 1.0f)
        return MathHelper.hsvToRgb(0.62222224f - f * 0.05f, 0.5f + f * 0.1f, 1.0f)
    }
}

/*
class JungleWetlandsBiome: Biome(
    Settings()
        .configureSurfaceBuilder(JourniaSurfaceBuilders.WETLANDS, JourniaSurfaceBuilders.WETLANDS_CONFIG)
        .precipitation(Precipitation.RAIN).category(Category.JUNGLE)
        .depth(-0.2f).scale(0.0f)
        .temperature(0.8f).downfall(0.95f)
        .effects(BiomeEffects.Builder()
            .fogColor(12638463)
            .waterColor(4159204)
            .waterFogColor(329011)
            .build())
        .parent("null")
) {

    init {
        addStructureFeature(
            JungleTempleFeature.JUNGLE_PYRAMID.configure(
                FeatureConfig.DEFAULT
            )
        )
        addStructureFeature(
            MineshaftFeature.MINESHAFT.configure(
                MineshaftFeatureConfig(0.004, MineshaftFeature.Type.NORMAL)
            )
        )
        addStructureFeature(
            StrongholdFeature.STRONGHOLD.configure(
                FeatureConfig.DEFAULT
            )
        )
        DefaultBiomeFeatures.addLandCarvers(this)
        // DefaultBiomeFeatures.addDefaultStructures(this)
        DefaultBiomeFeatures.addDefaultLakes(this)
        DefaultBiomeFeatures.addDungeons(this)
        DefaultBiomeFeatures.addMineables(this)
        DefaultBiomeFeatures.addDefaultOres(this)
        DefaultBiomeFeatures.addBamboo(this)
        DefaultBiomeFeatures.addExtraDefaultFlowers(this)
        DefaultBiomeFeatures.addJungleGrass(this)
        DefaultBiomeFeatures.addDefaultMushrooms(this)
        DefaultBiomeFeatures.addDefaultVegetation(this)
        DefaultBiomeFeatures.addSprings(this)
        DefaultBiomeFeatures.addJungleVegetation(this)
        DefaultBiomeFeatures.addFrozenTopLayer(this)

        addFeature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            Feature.SEAGRASS.configure(
                SeagrassFeatureConfig(
                    256,
                    0.6
                )
            ).createDecoratedFeature(
                Decorator.TOP_SOLID_HEIGHTMAP.configure(
                    DecoratorConfig.DEFAULT
                )
            )
        )
        addJungleWetlandsTrees()
        addJungleWetlandsDisks()

        addSpawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.SHEEP, 6, 4, 4))
        addSpawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.PIG, 5, 4, 4))
        addSpawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.CHICKEN, 5, 4, 4))
        addSpawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.COW, 4, 4, 4))
        addSpawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.PARROT, 40, 4, 8))
        addSpawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.PANDA, 1, 1, 2))
        addSpawn(SpawnGroup.AMBIENT, SpawnEntry(EntityType.BAT, 10, 8, 8))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.SPIDER, 100, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.ZOMBIE, 95, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.SKELETON, 100, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.CREEPER, 100, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.SLIME, 100, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.ENDERMAN, 10, 1, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.WITCH, 5, 1, 1))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.OCELOT, 2, 1, 3))
        addSpawn(SpawnGroup.WATER_CREATURE, SpawnEntry(EntityType.TROPICAL_FISH, 5, 6, 12))
        addSpawn(SpawnGroup.WATER_CREATURE, SpawnEntry(EntityType.TROPICAL_FISH, 10, 2, 5))

    }

    fun addBamboo() {
        addFeature(
            GenerationStep.Feature.VEGETAL_DECORATION, Feature.BAMBOO.configure(
                ProbabilityConfig(0.0f)
            ).createDecoratedFeature(Decorator.COUNT_HEIGHTMAP_DOUBLE.configure(CountDecoratorConfig(64)))
        )
    }

    fun addJungleWetlandsDisks() {
        addFeature(
            GenerationStep.Feature.UNDERGROUND_ORES,
            Feature.DISK.configure(
                DiskFeatureConfig(
                    Blocks.CLAY.defaultState,
                    4,
                    1,
                    arrayListOf(
                        Blocks.DIRT.defaultState, Blocks.CLAY.defaultState, JourniaBlocks.MUD.defaultState
                    )
                )
            ).createDecoratedFeature(
                Decorator.COUNT_TOP_SOLID.configure(
                    CountDecoratorConfig(2)
                )
            )
        )
        addFeature(
            GenerationStep.Feature.UNDERGROUND_ORES,
            Feature.DISK.configure(
                DiskFeatureConfig(
                    JourniaBlocks.MUD.defaultState,
                    8,
                    2,
                    Lists.newArrayList(
                        Blocks.GRASS_BLOCK.defaultState, Blocks.DIRT.defaultState
                    )
                )
            ).createDecoratedFeature(
                Decorator.COUNT_TOP_SOLID.configure(
                    CountDecoratorConfig(8)
                )
            )
        )
    }

    private fun addJungleWetlandsTrees() {
        addFeature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            Feature.RANDOM_SELECTOR.configure(
                RandomFeatureConfig(
                    ImmutableList.of<RandomFeatureEntry<*>>(
                        Feature.TREE.configure(DefaultBiomeFeatures.FANCY_TREE_CONFIG).withChance(0.1f),
                        Feature.TREE.configure(
                            DefaultBiomeFeatures.JUNGLE_GROUND_BUSH_CONFIG
                        ).withChance(0.5f)
                    ), Feature.TREE.configure(
                        DefaultBiomeFeatures.JUNGLE_TREE_CONFIG
                    )
                )
            ).createDecoratedFeature(
                Decorator.COUNT_EXTRA_HEIGHTMAP.configure(
                    CountExtraChanceDecoratorConfig(
                        20,
                        0.1f,
                        1
                    )
                )
            )
        )
    }
}

class ShatteredBadlandsPlateauBiome: Biome(
    Settings()
        .configureSurfaceBuilder(SurfaceBuilder.BADLANDS, SurfaceBuilder.BADLANDS_CONFIG)
        .precipitation(Precipitation.NONE)
        .category(Category.MESA)
        .depth(1.05F).scale(1.2125001F)
        .temperature(2.0f)
        .downfall(0.0f)
        .effects(
            BiomeEffects.Builder()
                .fogColor(12638463)
                .waterColor(4159204)
                .waterFogColor(329011)
                .build())
        .parent("null")
) {

    init {
        addStructureFeature(
            MineshaftFeature.MINESHAFT.configure(
                MineshaftFeatureConfig(0.004, MineshaftFeature.Type.MESA)
            )
        )
        addStructureFeature(
            StrongholdFeature.STRONGHOLD.configure(
                FeatureConfig.DEFAULT
            )
        )
        DefaultBiomeFeatures.addLandCarvers(this)
        DefaultBiomeFeatures.addDungeons(this)
        DefaultBiomeFeatures.addDefaultLakes(this)
        DefaultBiomeFeatures.addDungeons(this)
        DefaultBiomeFeatures.addMineables(this)
        DefaultBiomeFeatures.addDefaultOres(this)
        DefaultBiomeFeatures.addDefaultDisks(this)
        DefaultBiomeFeatures.addBadlandsGrass(this)
        DefaultBiomeFeatures.addDefaultMushrooms(this)
        DefaultBiomeFeatures.addBadlandsVegetation(this)
        DefaultBiomeFeatures.addSprings(this)
        DefaultBiomeFeatures.addFrozenTopLayer(this)

        addExtraGoldOre()

        addSpawn(SpawnGroup.AMBIENT, SpawnEntry(EntityType.BAT, 10, 8, 8))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.SPIDER, 100, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.ZOMBIE, 95, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.SKELETON, 100, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.CREEPER, 100, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.SLIME, 100, 4, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.ENDERMAN, 10, 1, 4))
        addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.WITCH, 5, 1, 1))
    }

    fun addExtraGoldOre() {
        addFeature(
            GenerationStep.Feature.UNDERGROUND_ORES, Feature.ORE.configure(
                OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Blocks.GOLD_ORE.defaultState, 9)
            ).createDecoratedFeature(Decorator.COUNT_RANGE.configure(RangeDecoratorConfig(32, 32, 32, 256)))
        )
    }
}
 */