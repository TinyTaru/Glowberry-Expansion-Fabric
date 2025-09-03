package com.tinytaru.glowberryexpansion

import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import com.tinytaru.glowberryexpansion.GlowberryExpansion
import net.minecraft.item.FoodComponent

/**
 * Kotlin 'object' = one global place to keep your items.
 */
object ModItems {

    // 1) Make the item instance. Settings() lets you tweak stack size, food, etc.
    val GLOWSTAFF: Item = Item(Item.Settings().maxCount(1)) // 1 per stack
    val BLUEGLOWBERRY: Item = Item(Item.Settings().maxCount(64).food(FoodComponent.Builder().hunger(2).saturationModifier(0.1f).build())) // 1 per stack
    // 2) Register items with Minecraft's registry
    fun registerAll() {
        register("glowstaff", GLOWSTAFF)
        register("blueglowberry", BLUEGLOWBERRY)
    }

    // --- helpers ---
    private fun register(name: String, item: Item): Item =
        Registry.register(Registries.ITEM, id(name), item)

    private fun id(path: String) =
        Identifier(GlowberryExpansion.MOD_ID, path)
}