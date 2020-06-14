package io.github.redstoneparadox.journia.world.gen.feature

import com.mojang.datafixers.util.Pair
import io.github.redstoneparadox.journia.util.concat
import net.minecraft.nbt.CompoundTag
import net.minecraft.structure.PoolStructurePiece
import net.minecraft.structure.StructureManager
import net.minecraft.structure.StructurePieceType
import net.minecraft.structure.StructureStart
import net.minecraft.structure.pool.SinglePoolElement
import net.minecraft.structure.pool.StructurePool
import net.minecraft.structure.pool.StructurePoolBasedGenerator
import net.minecraft.structure.pool.StructurePoolElement
import net.minecraft.util.BlockRotation
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockBox
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.DefaultFeatureConfig
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.StructureFeature

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

    class DungeonStructureStart(feature: StructureFeature<DefaultFeatureConfig>, chunkX: Int, chunkZ: Int, box: BlockBox, references: Int, seed: Long): StructureStart<DefaultFeatureConfig>(feature, chunkX, chunkZ, box, references, seed) {
        override fun init(chunkGenerator: ChunkGenerator?, structureManager: StructureManager?, x: Int, z: Int, biome: Biome?, featureConfig: DefaultFeatureConfig?) {
            StructurePoolBasedGenerator.addPieces(MAIN_POOL, 7, ::DungeonPiece, chunkGenerator, structureManager, BlockPos(x * 16, 0, z * 16), children, random, true, true)
            setBoundingBoxFromChildren()
        }

        companion object {
            val MAIN_POOL: Identifier = Identifier("journia:dungeon_main_room")
            val HALLWAYS_ROOMS_POOL: Identifier = Identifier("journia:dungeon_rooms_or_halls")
            val ROOMS_POOL: Identifier = Identifier("journia:dungeon_rooms")

            init {
                pool(
                    MAIN_POOL,
                    mainRooms()
                )

                pool(
                    HALLWAYS_ROOMS_POOL,
                    halls().concat(rooms())
                )

                pool(
                    ROOMS_POOL,
                    rooms()
                )
            }

            private fun pool(identifier: Identifier, elementCounts: List<Pair<StructurePoolElement, Int>>) {
                StructurePoolBasedGenerator.REGISTRY.add(
                    StructurePool(
                        identifier,
                        Identifier("empty"),
                        elementCounts,
                        StructurePool.Projection.RIGID
                    )
                )
            }

            private fun mainRooms(): MutableList<Pair<StructurePoolElement, Int>> {
                return mutableListOf(
                    Pair(SinglePoolElement("journia:dungeon/standard/main_rooms/dungeon_3_stories"), 1),
                    Pair(SinglePoolElement("journia:dungeon/standard/main_rooms/dungeon_t_stairs"), 1)
                ) as MutableList<Pair<StructurePoolElement, Int>>
            }

            private fun halls(): MutableList<Pair<StructurePoolElement, Int>> {
                return mutableListOf(
                    Pair(SinglePoolElement("journia:dungeon/standard/halls/dungeon_hall_long"), 1),
                    Pair(SinglePoolElement("journia:dungeon/standard/halls/dungeon_hall_short"), 2)
                ) as MutableList<Pair<StructurePoolElement, Int>>
            }

            private fun rooms(): MutableList<Pair<StructurePoolElement, Int>> {
                return mutableListOf(
                    Pair(SinglePoolElement("journia:dungeon/standard/rooms/dungeon_room_small"), 1)
                ) as MutableList<Pair<StructurePoolElement, Int>>
            }
        }
    }

    class DungeonPiece: PoolStructurePiece {
        constructor(manager: StructureManager, poolElement: StructurePoolElement, pos: BlockPos, groundLevelDelta: Int, rotation: BlockRotation, boundingBox: BlockBox):
                super(NEW_DUNGEON_PIECE, manager, poolElement, pos, groundLevelDelta, rotation, boundingBox)

        constructor(manager: StructureManager, tag: CompoundTag):
                super(manager, tag, NEW_DUNGEON_PIECE)


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