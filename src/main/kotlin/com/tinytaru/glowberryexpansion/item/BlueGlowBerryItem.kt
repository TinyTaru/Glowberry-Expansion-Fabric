package com.tinytaru.glowberryexpansion.item

import com.tinytaru.glowberryexpansion.ModBlocks
import net.minecraft.block.CaveVines
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World

class BlueGlowBerryItem(settings: Settings) : Item(settings) {
    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        val world = context.world
        val pos = context.blockPos
        val side = context.side
        val player = context.player
        
        // Try to place the vine if clicking on the bottom side of a block
        if (side == Direction.DOWN) {
            val placePos = pos.down()
            val abovePos = placePos.up()
            
            // Check if there's a solid block above to attach the vine to
            if (world.getBlockState(abovePos).isSideSolidFullSquare(world, abovePos, Direction.DOWN) &&
                world.getBlockState(placePos).isAir) {
                
                // Place the vine
                world.setBlockState(placePos, ModBlocks.BLUE_CAVE_VINES_TIP.defaultState
                    .with(CaveVines.BERRIES, false), 2)
                
                // Play placement sound
                world.playSound(
                    player, 
                    placePos, 
                    SoundEvents.BLOCK_CAVE_VINES_PLACE, 
                    SoundCategory.BLOCKS, 
                    1.0f, 
                    1.0f
                )
                
                // Consume the item if not in creative mode
                if (player?.isCreative == false) {
                    context.stack.decrement(1)
                }
                
                return ActionResult.SUCCESS
            }
        }
        
        // If placement fails, try to eat the berry
        if (player?.canConsume(false) == true) {
            player.setCurrentHand(context.hand)
            return ActionResult.CONSUME
        }
        
        return ActionResult.PASS
    }
    
    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val itemStack = user.getStackInHand(hand)
        
        if (user.canConsume(false)) {
            user.setCurrentHand(hand)
            return TypedActionResult.consume(itemStack)
        }
        
        return TypedActionResult.fail(itemStack)
    }
    
    override fun getMaxUseTime(stack: ItemStack): Int {
        return 32 // Same as food eating time
    }
    
    override fun finishUsing(stack: ItemStack, world: World, user: LivingEntity): ItemStack {
        return if (user is PlayerEntity && !world.isClient) {
            user.eatFood(world, stack)
        } else {
            stack
        }
    }
}
