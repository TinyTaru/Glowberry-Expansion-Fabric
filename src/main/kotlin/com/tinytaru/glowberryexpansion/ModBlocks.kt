package com.tinytaru.glowberryexpansion

import com.tinytaru.glowberryexpansion.block.BlueCaveVinesBodyBlock
import com.tinytaru.glowberryexpansion.block.BlueCaveVinesHeadBlock
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier

/**
 * Holds your custom blocks and registers them with Minecraft.
 */
object ModBlocks {

    // Our glowing stone: behaves like chiseled stone bricks, but emits light.
    val GLOWING_CHISELED_STONE: Block = Block(
        FabricBlockSettings.copyOf(Blocks.CHISELED_STONE_BRICKS)
            .luminance { 12 }
            .strength(1.5f, 6.0f)
            .sounds(BlockSoundGroup.STONE)
            .requiresTool()
    )

    private val GLOWING_CHISELED_STONE_ITEM = BlockItem(
        GLOWING_CHISELED_STONE,
        Item.Settings()
    )

    // Blue glowberry vines (body) and tip (head)
    lateinit var BLUE_CAVE_VINES: Block
    lateinit var BLUE_CAVE_VINES_TIP: Block

    init {
        // Create body and head using settings-only constructors
        BLUE_CAVE_VINES = BlueCaveVinesBodyBlock(
            FabricBlockSettings.copyOf(Blocks.CAVE_VINES_PLANT).noCollision().nonOpaque()
        )
        BLUE_CAVE_VINES_TIP = BlueCaveVinesHeadBlock(
            FabricBlockSettings.copyOf(Blocks.CAVE_VINES).noCollision().nonOpaque().ticksRandomly()
        )
    }

    private val BLUE_CAVE_VINES_TIP_ITEM = BlockItem(
        BLUE_CAVE_VINES_TIP,
        Item.Settings()
    )

    fun registerAll() {
        // Glowing stone
        registerBlock("glowing_chiseled_stone", GLOWING_CHISELED_STONE)
        registerItem("glowing_chiseled_stone", GLOWING_CHISELED_STONE_ITEM)

        // Blue glowberry vines
        registerBlock("blue_cave_vines", BLUE_CAVE_VINES)
        registerBlock("blue_cave_vines_tip", BLUE_CAVE_VINES_TIP)
        registerItem("blue_cave_vines_tip", BLUE_CAVE_VINES_TIP_ITEM)
    }

    // --- helpers ---
    private fun registerBlock(name: String, block: Block): Block =
        Registry.register(Registries.BLOCK, id(name), block)

    private fun registerItem(name: String, item: Item): Item =
        Registry.register(Registries.ITEM, id(name), item)

    private fun id(path: String) = Identifier(GlowberryExpansion.MOD_ID, path)
}