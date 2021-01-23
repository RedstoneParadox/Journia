package io.github.redstoneparadox.journia.entity

import com.terraformersmc.terraform.boat.TerraformBoat
import com.terraformersmc.terraform.boat.TerraformBoatEntity
import com.terraformersmc.terraform.boat.TerraformBoatItem
import io.github.redstoneparadox.journia.item.JourniaItems
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.vehicle.BoatEntity
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object JourniaEntityTypes {
    val PINE_BOAT: EntityType<TerraformBoatEntity> = BoatBuilder()
        .name("pine")
        .planks(JourniaItems.PINE_PLANKS)
        .item(JourniaItems.PINE_BOAT)
        .type(BoatEntity.Type.SPRUCE)
        .build()

    val CUBEN_BOAT: EntityType<TerraformBoatEntity> = BoatBuilder()
        .name("cuben")
        .planks(JourniaItems.CUBEN_PLANKS)
        .item(JourniaItems.CUBEN_BOAT)
        .type(BoatEntity.Type.OAK)
        .build()

    fun registerAll() {
        register("pine_boat", PINE_BOAT)
        register("cuben_boat", CUBEN_BOAT)
    }

    private fun register(id: String, type: EntityType<*>) {
        Registry.register(Registry.ENTITY_TYPE, Identifier("journia:$id"), type)
    }

    class BoatBuilder {
        private var name: String = "missingno"
        private var planks: Item = Items.OAK_PLANKS
        private var item: Item = Items.OAK_BOAT
        private var type: BoatEntity.Type = BoatEntity.Type.OAK

        fun name(name: String): BoatBuilder {
            this.name = name
            return this
        }

        fun planks(planks: Item): BoatBuilder {
            this.planks = planks
            return this
        }

        fun item(item: TerraformBoatItem): BoatBuilder {
            this.item = item
            return this
        }

        fun type(type: BoatEntity.Type): BoatBuilder {
            this.type = type
            return this
        }

        fun build(): EntityType<TerraformBoatEntity> {
            val skin = Identifier("journia", "textures/entity/boat/$name.png")
            val boat = TerraformBoat(item, planks, skin, type)

            return FabricEntityTypeBuilder
                .create<TerraformBoatEntity>(SpawnGroup.MISC) { type, world -> TerraformBoatEntity(type, world, boat) }
                .dimensions(EntityDimensions.fixed(1.375f, 0.5625f))
                .build()
        }
    }
}