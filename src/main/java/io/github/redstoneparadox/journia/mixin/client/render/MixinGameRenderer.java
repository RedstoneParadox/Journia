package io.github.redstoneparadox.journia.mixin.client.render;

import io.github.redstoneparadox.journia.world.biome.WastelandBiome;
import io.github.redstoneparadox.journia.world.biome.WastelandEdgeBiome;
import io.github.redstoneparadox.journia.world.biome.WastelandRiverBiome;
import io.github.redstoneparadox.journia.world.biome.WastelandShoreBiome;
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

}
