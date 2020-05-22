package io.github.redstoneparadox.journia.block

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Blocks
import net.minecraft.block.MushroomBlock
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockView
import net.minecraft.world.World

class GreenMushroomBlock: MushroomBlock(FabricBlockSettings.copy(Blocks.RED_MUSHROOM_BLOCK)) {
    override fun onLandedUpon(world: World, pos: BlockPos, entity: Entity, distance: Float) {
        if (entity.bypassesLandingEffects()) {
            super.onLandedUpon(world, pos, entity, distance)
        } else {
            entity.handleFallDamage(distance, 0.0f)
        }
    }

    override fun onEntityLand(world: BlockView, entity: Entity) {
        if (entity.bypassesLandingEffects()) {
            super.onEntityLand(world, entity)
        } else {
            bounce(entity)
        }
    }

    private fun bounce(entity: Entity) {
        val vec3d = entity.velocity
        if (vec3d.y < 0.0) {
            val d = if (entity is LivingEntity) 1.0 else 0.8
            entity.setVelocity(vec3d.x, -vec3d.y * d, vec3d.z)
        }
    }
}