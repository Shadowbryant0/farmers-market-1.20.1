package net.shadow.farmersmarket.event;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.Fertilizable;
import net.minecraft.command.argument.serialize.FloatArgumentSerializer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ai.brain.task.BoneMealTask;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldEvents;
import net.shadow.farmersmarket.FarmersMarket;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.ModItems;

public class FarmersMarketEvents implements ModInitializer{

    @Override
    public void onInitialize() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {

            ItemStack stack = player.getStackInHand(hand);
            if (!(stack.getItem() instanceof HoeItem)) {
                return ActionResult.PASS;
            }

            // Replace with your enchantment registration reference
            if (EnchantmentHelper.getLevel(FarmersMarketEnchants.FreshFeildsEnchantment, stack) <= 0) {
                return ActionResult.PASS;
            }


            BlockPos pos = hitResult.getBlockPos();
            BlockState state = world.getBlockState(pos);
            int age = state.get(CropBlock.AGE);
            if (age == 6) {


                return ActionResult.PASS;
            }
            Fertilizable fertilizable = (Fertilizable) state.getBlock();

                    fertilizable.grow((ServerWorld)world, world.random, pos, state);

            return ActionResult.SUCCESS;
        });

    }

}