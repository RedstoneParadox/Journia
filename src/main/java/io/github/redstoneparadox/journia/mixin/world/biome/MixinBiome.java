package io.github.redstoneparadox.journia.mixin.world.biome;

import io.github.redstoneparadox.journia.JourniaKt;
import io.github.redstoneparadox.journia.world.biome.JourniaBiomes;
import io.github.redstoneparadox.journia.world.biome.WastelandBiome;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public abstract class MixinBiome {
    @Inject(method = "calculateSkyColor", at = @At("HEAD"), cancellable = true)
    private void calculateSkyColor(CallbackInfoReturnable<Integer> cir) {
        Biome self = (Biome)(Object)this;
        if (self instanceof WastelandBiome) {
            cir.setReturnValue(JourniaKt.colorToInt(1.0, 1.0, 0.85));
            cir.cancel();
        }
    }
}
