package io.github.redstoneparadox.journia.mixin.client.render;

import io.github.redstoneparadox.journia.world.biome.WastelandBiome;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer {

    @Inject(at = @At("RETURN"), method = "getViewDistance()F", cancellable = true)
    private void getWastelandsViewDistance(CallbackInfoReturnable<Float> cir) {
        final MinecraftClient client = MinecraftClient.getInstance();
        final World world = client.world;
        final PlayerEntity player = client.player;

        int count = 0;

        for (int x = 0; x < 9; x++) {
            for (int z = 0; z < 9; z++) {
                BlockPos pos = player.getBlockPos().add(x - 4, 0, z - 4);
                if (world.getBiome(pos) instanceof WastelandBiome) count += 1;
            }
        }

        float distance = cir.getReturnValue();
        float intensity = (count/81f);
        float newDistance = distance - intensity * (distance - 4);

        if (newDistance < distance) {
            cir.setReturnValue(newDistance);
        }
    }
}
