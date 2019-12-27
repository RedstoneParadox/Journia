package io.github.redstoneparadox.world.gen.foliage

import com.mojang.datafixers.Dynamic
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.ModifiableTestableWorld
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig
import net.minecraft.world.gen.foliage.FoliagePlacer
import net.minecraft.world.gen.foliage.FoliagePlacerType
import java.util.*
import java.util.function.Function
import kotlin.math.abs

class PineFoliagePlacer(radius: Int, randomRadius: Int): FoliagePlacer(radius, randomRadius, TYPE) {

    override fun generate(world: ModifiableTestableWorld, random: Random, config: BranchedTreeFeatureConfig, i: Int, j: Int, k: Int, pos: BlockPos, positions: MutableSet<BlockPos>) {

    }

    override fun getRadius(random: Random, i: Int, j: Int, config: BranchedTreeFeatureConfig?): Int {
        if (randomRadius <= 0) return radius
        return radius + random.nextInt(randomRadius)
    }

    override fun method_23447(i: Int, j: Int, k: Int, l: Int): Int {
        return if (l <= 1) 0 else 2
    }

    override fun method_23451(random: Random?, i: Int, j: Int, k: Int, l: Int, m: Int): Boolean {
        return abs(j) == m && abs(l) == m && m > 0
    }

    companion object {
        val TYPE: FoliagePlacerType<PineFoliagePlacer> = register()

        private fun register(): FoliagePlacerType<PineFoliagePlacer> {
            val clazz = FoliagePlacerType::class.java
            val reg = clazz.getDeclaredMethod("register", String::class.java, Function::class.java)
            reg.isAccessible = true
            return reg.invoke(null, "journia:pine", Function { dynamic: Dynamic<*> -> deserialize(dynamic) }) as FoliagePlacerType<PineFoliagePlacer>
        }

        private fun deserialize(dynamic: Dynamic<*>): PineFoliagePlacer {
            val radius = dynamic["radius"].map {
                val value = it.value
                if (value is Int) value else 3
            }.orElse(3)
            val randomRadius = dynamic["random_radius"].map {
                val value = it.value
                if (value is Int) value else 3
            }.orElse(3)
            return PineFoliagePlacer(radius, randomRadius)
        }
    }
}