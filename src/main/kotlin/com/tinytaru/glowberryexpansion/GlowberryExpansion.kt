package com.tinytaru.glowberryexpansion

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object GlowberryExpansion : ModInitializer {
    const val MOD_ID = "glowberryexpansion"
    private val logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        // Register all mod items
        ModItems.registerAll()
        
        // Register the custom item group
        ModTabs.register()

        // Register all mod blocks
        ModBlocks.registerAll()
        
        logger.info("Glowberry Expansion loaded!")
    }
}