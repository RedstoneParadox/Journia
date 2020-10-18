package io.github.redstoneparadox.journia.world.gen.feature

import net.minecraft.nbt.CompoundTag
import net.minecraft.structure.PoolStructurePiece
import net.minecraft.structure.StructureManager
import net.minecraft.structure.StructurePieceType
import net.minecraft.structure.StructureStart
import net.minecraft.structure.pool.StructurePoolBasedGenerator
import net.minecraft.structure.pool.StructurePoolElement
import net.minecraft.util.BlockRotation
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockBox
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.DynamicRegistryManager
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.DefaultFeatureConfig
import net.minecraft.world.gen.feature.StructureFeature
import net.minecraft.world.gen.feature.StructureFeature.StructureStartFactory
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig

class DungeonFeature: StructureFeature<DefaultFeatureConfig>(DefaultFeatureConfig.CODEC) {
    override fun getName(): String {
        return "New Dungeon"
    }

    override fun getStructureStartFactory(): StructureStartFactory<DefaultFeatureConfig> {
        return StructureStartFactory { structureFeature: StructureFeature<DefaultFeatureConfig>, i: Int, i1: Int, blockBox: BlockBox, i2: Int, l: Long ->
            DungeonStructureStart(
                structureFeature,
                i,
                i1,
                blockBox,
                i2,
                l
            )
        }
    }

    class DungeonStructureStart(
        feature: StructureFeature<DefaultFeatureConfig>,
        chunkX: Int,
        chunkZ: Int,
        box: BlockBox,
        references: Int,
        seed: Long
    ): StructureStart<DefaultFeatureConfig>(feature, chunkX, chunkZ, box, references, seed) {
        override fun init(
            registryManager: DynamicRegistryManager,
            chunkGenerator: ChunkGenerator,
            structureManager: StructureManager,
            chunkX: Int,
            chunkZ: Int,
            biome: Biome,
            config: DefaultFeatureConfig
        ) {
            val blockpos = BlockPos(chunkX * 16, 62, chunkZ * 16)
            StructurePoolBasedGenerator.method_30419(
                registryManager,
                StructurePoolFeatureConfig({ registryManager[Registry.TEMPLATE_POOL_WORLDGEN][START_POOL] }, 1),
                ::PoolStructurePiece,
                chunkGenerator,
                structureManager,
                blockpos,
                this.children,
                random,
                true,
                true
                )
        }

        companion object {
            val START_POOL: Identifier = Identifier("journia:dungeon/main_room")
            val HALLWAYS_ROOMS_POOL: Identifier = Identifier("journia:dungeon/rooms_or_halls")
            val ROOMS_POOL: Identifier = Identifier("journia:dungeon/rooms")
        }
    }

    class DungeonPiece: PoolStructurePiece {
        constructor(
            manager: StructureManager,
            poolElement: StructurePoolElement,
            pos: BlockPos,
            groundLevelDelta: Int,
            rotation: BlockRotation,
            boundingBox: BlockBox
        ):
                super(manager, poolElement, pos, groundLevelDelta, rotation, boundingBox)

        constructor(manager: StructureManager, tag: CompoundTag):
                super(manager, tag)
    }

    companion object {
        val NEW_DUNGEON_STRUCTURE_FEATURE: StructureFeature<DefaultFeatureConfig> = Registry.register(
            Registry.STRUCTURE_FEATURE,
            Identifier("journia:dungeon_structure"),
            DungeonFeature()
        )

        val NEW_DUNGEON_PIECE: StructurePieceType = Registry.register(
            Registry.STRUCTURE_PIECE,
            Identifier("journia", "dungeon_piece"),
            StructurePieceType { manager, tag -> DungeonPiece(manager, tag) }
        )
    }
}