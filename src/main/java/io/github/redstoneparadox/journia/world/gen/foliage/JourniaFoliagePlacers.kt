package io.github.redstoneparadox.journia.world.gen.foliage

import io.github.redstoneparadox.journia.mixin.world.gen.foliage.AccessorFoliageType
import net.minecraft.world.gen.foliage.FoliagePlacerType

object JourniaFoliagePlacers {
    val PINE_FOLIAGE_PLACER: FoliagePlacerType<PineFoliagePlacer> = AccessorFoliageType.callRegister("journia:pine_foliage_placer") { PineFoliagePlacer(it) }
    val GROENWOOD_FOLIAGE_PLACER: FoliagePlacerType<GroenwoodFoliagePlacer> = AccessorFoliageType.callRegister("journia:groenwood_foliage") { GroenwoodFoliagePlacer(it) }
}