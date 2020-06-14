package io.github.redstoneparadox.journia.mixin.world.gen.foliage;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;


@Mixin(FoliagePlacerType.class)
public interface AccessorFoliageType {

    @Invoker(value = "method_28850")
    static <P extends FoliagePlacer> FoliagePlacerType<P> callRegister(String id, Codec<P> codec) {
        throw new AssertionError("Mixin dummy");
    }
}
