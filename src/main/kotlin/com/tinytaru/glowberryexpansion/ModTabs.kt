package com.tinytaru.glowberryexpansion

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier

object ModTabs {
    val GLOWBERRY_GROUP: ItemGroup = FabricItemGroup.builder()
        .icon { ItemStack(ModItems.BLUEGLOWBERRY) }
        .displayName(Text.translatable("itemGroup.glowberryexpansion.glowberry_group"))
        .entries { _, entries ->
            entries.add(ModItems.GLOWSTAFF)
            entries.add(ModItems.BLUEGLOWBERRY)
            entries.add(ModBlocks.BLUE_CAVE_VINES_TIP)
            entries.add(ModBlocks.GLOWING_CHISELED_STONE)
        }
        .build()

    fun register() {
        Registry.register(
            Registries.ITEM_GROUP,
            Identifier(GlowberryExpansion.MOD_ID, "glowberry_group"),
            GLOWBERRY_GROUP
        )
    }
}