package io.github.redstoneparadox.journia.world.gen.foliage

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.terraformersmc.shapes.api.Position
import com.terraformersmc.shapes.api.layer.TransformationLayer
import com.terraformersmc.shapes.impl.Shapes
import com.terraformersmc.shapes.impl.layer.transform.TranslateLayer
import io.github.redstoneparadox.journia.asElse
import io.github.redstoneparadox.journia.block.JourniaBlocks
import io.github.redstoneparadox.journia.util.field
import io.github.redstoneparadox.journia.world.gen.filler.PredicateFiller
import net.minecraft.util.math.BlockBox
import net.minecraft.util.math.BlockPos
import net.minecraft.world.ModifiableTestableWorld
import net.minecraft.world.gen.feature.TreeFeatureConfig
import net.minecraft.world.gen.foliage.FoliagePlacer
import net.minecraft.world.gen.foliage.FoliagePlacerType
import java.util.*

class MegaPineFoliagePlacer(val minHeight: Int, val additionalHeight: Int, radius: Int, randomRadius: Int, offset: Int, randomOffset: Int): FoliagePlacer(radius, randomRadius, offset, randomOffset) {
    override fun generate(world: ModifiableTestableWorld, random: Random, config: TreeFeatureConfig, trunkHeight: Int, treeNode: TreeNode, foliageHeight: Int, radius: Int, leaves: MutableSet<BlockPos>, i: Int, blockBox: BlockBox) {
        if (treeNode.isGiantTrunk) {
            val chosenRadius = random.nextInt(randomRadius) + radius
            val trueRadius = chosenRadius.takeIf { it % 2 == 0 }.asElse(chosenRadius + 1)

            val cone = Shapes.ellipticalPyramid(trueRadius.toDouble(), trueRadius.toDouble(), foliageHeight.toDouble())
            cone.applyLayer(TranslateLayer.of(Position.of(treeNode.center)))
            cone.fill(PredicateFiller(world, JourniaBlocks.PINE_LEAVES.defaultState) { it.isAir })
        }
    }

    override fun getHeight(random: Random?, trunkHeight: Int, config: TreeFeatureConfig?): Int {
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

    companion object {
        val CODEC: Codec<MegaPineFoliagePlacer> = RecordCodecBuilder.create { instance ->
            return@create instance.group(
                Codec.INT.field("min_height") { minHeight },
                Codec.INT.field("additional_height") { additionalHeight },
                Codec.INT.fieldOf("radius").forGetter { it.radius },
                Codec.INT.fieldOf("radius_random").forGetter { it.randomRadius },
                Codec.INT.fieldOf("offset").forGetter { it.offset },
                Codec.INT.fieldOf("offset_random").forGetter { it.randomOffset }
            ).apply(instance, ::MegaPineFoliagePlacer)
        }
    }
}