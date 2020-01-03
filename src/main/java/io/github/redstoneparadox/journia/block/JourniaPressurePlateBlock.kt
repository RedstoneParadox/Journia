package io.github.redstoneparadox.journia.block

import net.minecraft.block.PressurePlateBlock

class JourniaPressurePlateBlock(rule: ActivationRule, settings: Settings) : PressurePlateBlock(rule, settings) {

    companion object {
        fun wood(settings: Settings): JourniaPressurePlateBlock {
            return JourniaPressurePlateBlock(ActivationRule.EVERYTHING, settings)
        }
    }
}