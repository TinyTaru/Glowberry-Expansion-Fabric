package com.tinytaru.glowberryexpansion

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.client.render.RenderLayer

object GlowberryExpansionClient : ClientModInitializer {
    override fun onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        // Render cutout for custom vines (transparent pixels)
        BlockRenderLayerMap.INSTANCE.putBlocks(
            RenderLayer.getCutout(),
            ModBlocks.BLUE_CAVE_VINES,
            ModBlocks.BLUE_CAVE_VINES_TIP
        )
    }
}