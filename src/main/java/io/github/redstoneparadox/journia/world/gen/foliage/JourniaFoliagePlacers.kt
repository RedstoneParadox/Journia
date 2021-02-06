package io.github.redstoneparadox.journia.world.gen.foliage

import io.github.redstoneparadox.journia.mixin.world.gen.foliage.AccessorFoliageType
import net.minecraft.world.gen.foliage.FoliagePlacerType

object JourniaFoliagePlacers {
    val PINE_FOLIAGE_PLACER: FoliagePlacerType<PineFoliagePlacer> = AccessorFoliageType.callRegister("journia:pine_foliage_placer", PineFoliagePlacer.CODEC)
    val MEGA_PINE_FOLIAGE_PLACER: FoliagePlacerType<MegaPineFoliagePlacer> = AccessorFoliageType.callRegister("journia:mega_pine_foliage_placer", MegaPineFoliagePlacer.CODEC)
}