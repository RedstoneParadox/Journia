package io.github.redstoneparadox.journia.world.gen.feature

import com.mojang.datafixers.Dynamic
import com.mojang.datafixers.util.Pair
import net.minecraft.nbt.CompoundTag
import net.minecraft.structure.*
import net.minecraft.structure.pool.SinglePoolElement
import net.minecraft.structure.pool.StructurePool
import net.minecraft.structure.pool.StructurePoolBasedGenerator
import net.minecraft.structure.pool.StructurePoolElement
import net.minecraft.util.BlockRotation
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockBox
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.ChunkPos
import net.minecraft.util.registry.Registry
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.source.BiomeAccess
import net.minecraft.world.gen.ChunkRandom
import net.minecraft.world.gen.StructureAccessor
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.DefaultFeatureConfig
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.StructureFeature
import java.util.*
import java.util.function.Function

class FortFeature(function: Function<Dynamic<*>, out DefaultFeatureConfig>): StructureFeature<DefaultFeatureConfig>(function) {
    override fun shouldStartAt(biomeAccess: BiomeAccess, chunkGenerator: ChunkGenerator, l: Long, chunkRandom: ChunkRandom, i: Int, j: Int, biome: Biome, chunkPos: ChunkPos): Boolean {
        return chunkRandom.nextInt(32) == 31
    }

    override fun getName(): String {
        return "Fort"
    }

    override fun getStructureStartFactory(): StructureStartFactory {
        return StructureStartFactory { structureFeature: StructureFeature<*>, i: Int, i1: Int, blockBox: BlockBox, i2: Int, l: Long -> FortStructureStart(structureFeature, i, i1, blockBox, i2, l) }
    }

    override fun getRadius(): Int {
        return 8
    }

    class FortStructureStart(feature: StructureFeature<*>, chunkX: Int, chunkZ: Int, box: BlockBox, references: Int, seed: Long): StructureStart(feature, chunkX, chunkZ, box, references, seed) {
        override fun init(chunkGenerator: ChunkGenerator, structureManager: StructureManager, x: Int, z: Int, biome: Biome) {
            StructurePoolBasedGenerator.addPieces(BASE_POOL, 7, ::FortPiece, chunkGenerator, structureManager, BlockPos(x * 16, 0, z * 16), children, random, true, true)
            setBoundingBoxFromChildren()
        }

        companion object {
            val BASE_POOL: Identifier = Identifier("journia:fort_base")
            val BASE_EDGE_POOL: Identifier = Identifier("journia:fort_base_edge")
            val WALL_POOL: Identifier = Identifier("journia:fort_wall")

            init {
                StructurePoolBasedGenerator.REGISTRY.add(
                    StructurePool(
                        BASE_POOL,
                        Identifier("empty"),
                        listOf(
                            Pair(SinglePoolElement("journia:fort/base/fort_base_2x2"), 1)
                        ) as List<Pair<StructurePoolElement, Int>>,
                        StructurePool.Projection.RIGID
                    )
                )

                StructurePoolBasedGenerator.REGISTRY.add(
                    StructurePool(
                        BASE_EDGE_POOL,
                        Identifier("empty"),
                        listOf(
                            Pair(SinglePoolElement("journia:fort/edge/fort_base_edge_short"), 1)
                        ) as List<Pair<StructurePoolElement, Int>>,
                        StructurePool.Projection.RIGID
                    )
                )

                StructurePoolBasedGenerator.REGISTRY.add(
                    StructurePool(
                        WALL_POOL,
                        Identifier("empty"),
                        listOf(
                            Pair(SinglePoolElement("journia:fort/wall/fort_wall"), 1)
                        ) as List<Pair<StructurePoolElement, Int>>,
                        StructurePool.Projection.RIGID
                    )
                )
            }
        }
    }

    class FortPiece: PoolStructurePiece {
        constructor(manager: StructureManager, poolElement: StructurePoolElement, pos: BlockPos, groundLevelDelta: Int, rotation: BlockRotation, boundingBox: BlockBox):
                super(FORT_PIECE, manager, poolElement, pos, groundLevelDelta, rotation, boundingBox)

        constructor(manager: StructureManager, tag: CompoundTag):
                super(manager, tag, FORT_PIECE)
    }

    companion object {
        val FORT_FEATURE: StructureFeature<DefaultFeatureConfig> = Registry.register(
            Registry.FEATURE,
            Identifier("journia:fort"),
            FortFeature(Function {DefaultFeatureConfig.deserialize(it)})
        )

        val FORT_STRUCTURE_FEATURE: StructureFeature<DefaultFeatureConfig> = Registry.register(
            Registry.STRUCTURE_FEATURE,
            Identifier("journia:fort_structure"),
            FORT_FEATURE
        )

        val FORT_PIECE: StructurePieceType = Registry.register(
            Registry.STRUCTURE_PIECE,
            Identifier("journia", "fort_piece"),
            StructurePieceType {manager, tag -> FortPiece(manager, tag)}
        )
    }
}