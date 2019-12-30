package io.github.redstoneparadox.journia.mixin.client.render;

import io.github.redstoneparadox.journia.world.biome.WastelandBiome;
import io.github.redstoneparadox.journia.world.biome.WastelandRiverBiome;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
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
                Biome biome = world.getBiome(pos);
                if (biome instanceof WastelandBiome || biome instanceof WastelandRiverBiome) count += 1;
            }
        }

        float distance = cir.getReturnValue();
        float intensity = (count/81f);
        float newDistance = distance - intensity * (distance - 8);

        if (newDistance < distance) {
            cir.setReturnValue(newDistance);
        }
    }
}
