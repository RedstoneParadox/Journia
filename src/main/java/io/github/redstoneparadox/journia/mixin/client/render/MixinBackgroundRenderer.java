package io.github.redstoneparadox.journia.mixin.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.redstoneparadox.journia.world.biome.JourniaBiomes;
import io.github.redstoneparadox.journia.world.biome.WastelandBiome;
import io.github.redstoneparadox.journia.world.biome.WastelandRiverBiome;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BackgroundRenderer.class)
public abstract class MixinBackgroundRenderer {

    @Shadow private static float red;
    private static Camera renderCam;
    private static ClientWorld renderWorld;

    @Inject(method = "render", at = @At("HEAD"))
    private static void captureParams(Camera camera, float f, ClientWorld clientWorld, int i, float g, CallbackInfo ci) {
        renderCam = camera;
        renderWorld = clientWorld;
    }

    @ModifyVariable(method = "render", name = "vec3d2", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/world/ClientWorld;getFogColor(F)Lnet/minecraft/util/math/Vec3d;"))
    private static Vec3d modifyColor(Vec3d vec3d2) {
        Vec3d color = vec3d2;
        if (renderWorld != null && renderCam != null && renderCam.getFocusedEntity() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) renderCam.getFocusedEntity();

            int count = 0;

            for (int x = 0; x < 9; x++) {
                for (int z = 0; z < 9; z++) {
                    BlockPos pos = entity.getBlockPos().add(x - 4, 0, z - 4);
                    Biome biome = renderWorld.getBiome(entity.getBlockPos());
                    if (biome instanceof WastelandBiome || biome instanceof WastelandRiverBiome) count += 1;
                }
            }
            float intensity = (count/81f);

            double deltaRed = 1.0 - vec3d2.x;
            double deltaGreen = 1.0 - vec3d2.y;
            double deltaBlue = 0.75 - vec3d2.z;

            color = new Vec3d(vec3d2.x + (deltaRed * intensity), vec3d2.y + (deltaGreen * intensity), vec3d2.z + (deltaBlue * intensity));
        }
        renderCam = null;
        renderWorld = null;
        return color;
    }
}
