package io.github.redstoneparadox.journia.world.gen.foliage

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.terraformersmc.terraform.shapes.api.Position
import com.terraformersmc.terraform.shapes.impl.Shapes
import com.terraformersmc.terraform.shapes.impl.layer.pathfinder.AddLayer
import com.terraformersmc.terraform.shapes.impl.layer.transform.TranslateLayer
import io.github.redstoneparadox.journia.asElse
import io.github.redstoneparadox.journia.util.field
import io.github.redstoneparadox.journia.world.gen.filler.SetFiller
import net.minecraft.block.BlockState
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockBox
import net.minecraft.util.math.BlockPos
import net.minecraft.world.ModifiableTestableWorld
import net.minecraft.world.gen.UniformIntDistribution
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.foliage.FoliagePlacer
import net.minecraft.world.gen.foliage.FoliagePlacerType
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.abs


class MegaPineFoliagePlacer(val minHeight: Int, val additionalHeight: Int, radius: UniformIntDistribution, offset: UniformIntDistribution): FoliagePlacer(radius, offset) {
    private val cached: MutableMap<Configuration, Set<Pair<BlockPos, BlockState>>> = HashMap()

    constructor(minHeight: Int, additionalHeight: Int, radius: Int, randomRadius: Int, offset: Int, randomOffset: Int):
            this(minHeight, additionalHeight, UniformIntDistribution.of(radius, randomRadius), UniformIntDistribution.of(offset, randomOffset))

    override fun generate(world: ModifiableTestableWorld, random: Random, config: TreeFeatureConfig, trunkHeight: Int, treeNode: TreeNode, foliageHeight: Int, radius: Int, leaves: MutableSet<BlockPos>, i: Int, blockBox: BlockBox) {
        if (treeNode.isGiantTrunk) {
            val trueRadius = radius.takeIf { it % 2 == 1 }.asElse(radius - 1)

            val leavesState = config.leavesProvider.getBlockState(random, treeNode.center)

            val positions = cached.computeIfAbsent(Configuration(foliageHeight, trueRadius)) {
                val cone = Shapes.ellipticalPyramid(trueRadius.toDouble(), trueRadius.toDouble(), foliageHeight.toDouble())
                val set = mutableSetOf<Pair<BlockPos, BlockState>>()

                cone
                    .applyLayer(AddLayer(cone.applyLayer(TranslateLayer(Position.of(1.0, 0.0, 0.0)))))
                    .applyLayer(AddLayer(cone.applyLayer(TranslateLayer(Position.of(0.0, 0.0, 1.0)))))
                    .applyLayer(AddLayer(cone.applyLayer(TranslateLayer(Position.of(1.0, 0.0, 1.0)))))
                    .fill(SetFiller(set) {
                        val distance = calculateActualDistance(it.x, it.y - (foliageHeight - 3), it.z)

                        withDistance(leavesState, distance)
                    })

                return@computeIfAbsent set
            }

            positions.forEach {
                val truePos = it.first.add(treeNode.center.down(foliageHeight - 3))
                world.setBlockState(truePos, it.second, 19, 32)
            }
        }
    }

    override fun getRandomHeight(random: Random?, trunkHeight: Int, config: TreeFeatureConfig?): Int {
        return random!!.nextInt(this.additionalHeight) + minHeight
    }

    override fun getType(): FoliagePlacerType<*> {
        return JourniaFoliagePlacers.MEGA_PINE_FOLIAGE_PLACER
    }

    override fun isInvalidForLeaves(random: Random, baseHeight: Int, dx: Int, dy: Int, dz: Int, bl: Boolean): Boolean {
        return if (baseHeight + dy >= 7) {
            true
        } else {
            baseHeight * baseHeight + dy * dy > dz * dz
        }
    }

    private fun withDistance(state: BlockState, distance: Int): BlockState {
        return state.with(Properties.DISTANCE_1_7, distance.coerceAtMost(7))
    }

    private fun calculateActualDistance(offsetX: Int, offsetY: Int, offsetZ: Int): Int {
        var distance = abs(offsetX) + abs(offsetZ)

        if (offsetY > 0) distance += offsetY
        if (offsetX < 0) distance -= 1
        if (offsetZ < 0) distance -= 1

        // If the distance is 0, the leaves wouldn't be there to begin with?
        return if (distance <= 0) 1 else distance
    }

    data class Configuration(val height: Int, val radius: Int)

    companion object {
        val CODEC: Codec<MegaPineFoliagePlacer> = RecordCodecBuilder.create { instance ->
            return@create instance.group(
                Codec.INT.field("min_height") { minHeight },
                Codec.INT.field("additional_height") { additionalHeight },
                UniformIntDistribution.CODEC.field("radius") { radius },
                UniformIntDistribution.CODEC.field("offset") { offset }
            ).apply(instance, ::MegaPineFoliagePlacer)
        }
    }
}