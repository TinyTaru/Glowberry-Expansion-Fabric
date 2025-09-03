package com.tinytaru.glowberryexpansion.block

import com.tinytaru.glowberryexpansion.ModBlocks
import com.tinytaru.glowberryexpansion.ModItems
import net.minecraft.block.Block
import net.minecraft.block.AbstractPlantStemBlock
import net.minecraft.block.BlockState
import net.minecraft.block.CaveVines
import net.minecraft.block.CaveVinesBodyBlock
import net.minecraft.block.CaveVinesHeadBlock
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

// Head block (tip) that drops BLUEGLOWBERRY instead of vanilla glow berries when picked
class BlueCaveVinesHeadBlock(settings: Settings) : CaveVinesHeadBlock(settings) {
    override fun getPlant(): Block {
        return ModBlocks.BLUE_CAVE_VINES
    }
    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        // Same logic as vanilla, but swap the dropped item
        val hasBerries = state.get(CaveVines.BERRIES)
        if (hasBerries) {
            // drop 1-2 blue glowberries
            val count = world.random.nextInt(2) + 1
            Block.dropStack(world, pos, ItemStack(ModItems.BLUEGLOWBERRY, count))
            world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0f, 1.0f)
            world.setBlockState(pos, state.with(CaveVines.BERRIES, false), Block.NOTIFY_LISTENERS)
            return ActionResult.success(world.isClient)
        }
        return super.onUse(state, world, pos, player, hand, hit)
    }
}

// Body block (plant) - behavior same as vanilla
class BlueCaveVinesBodyBlock(settings: Settings) : CaveVinesBodyBlock(settings)
{
    override fun getStem(): AbstractPlantStemBlock {
        return ModBlocks.BLUE_CAVE_VINES_TIP as AbstractPlantStemBlock
    }
    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        val hasBerries = state.get(CaveVines.BERRIES)
        if (hasBerries) {
            val count = world.random.nextInt(2) + 1
            Block.dropStack(world, pos, ItemStack(ModItems.BLUEGLOWBERRY, count))
            world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0f, 1.0f)
            world.setBlockState(pos, state.with(CaveVines.BERRIES, false), Block.NOTIFY_LISTENERS)
            return ActionResult.success(world.isClient)
        }
        return super.onUse(state, world, pos, player, hand, hit)
    }
}
