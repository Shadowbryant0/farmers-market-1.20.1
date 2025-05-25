package net.shadow.farmersmarket.event;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;

public class FarmersMarketEvents implements ModInitializer{

    @Override
    public void onInitialize() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            // Only on server side & main hand
            if (hand != Hand.MAIN_HAND || world.isClient()) {
                return ActionResult.PASS;
            }

            ItemStack stack = player.getStackInHand(hand);
            if (!(stack.getItem() instanceof HoeItem)) {
                return ActionResult.PASS;
            }

            // Replace with your enchantment registration reference
            if (EnchantmentHelper.getLevel(FarmersMarketEnchants.FreshFeildsEnchantment, stack) <= 0) {
                return ActionResult.PASS;
            }

            BlockHitResult blockHit = (BlockHitResult) hitResult;
            BlockPos placedPos = blockHit.getBlockPos().offset(blockHit.getSide());

            BlockState blockState = world.getBlockState(placedPos);
            Block block = blockState.getBlock();


            return ActionResult.PASS;
        });
    }
}