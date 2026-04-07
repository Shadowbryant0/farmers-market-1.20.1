package net.shadow.farmersmarket.event;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;


public class FreshFieldsEvent {


        public static void register() {
            PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
                if (!(world instanceof ServerWorld serverWorld)) return;
                ItemStack tool = player.getMainHandStack();
                if (!tool.isSuitableFor(state)) return;
                if(state.getBlock() instanceof CropBlock crop) {
                    if(!(crop.getAge(state) == crop.getMaxAge())) return;
                    BlockState defaultstate = crop.getDefaultState();

                    if(EnchantmentHelper.getLevel(FarmersMarketEnchants.FreshFeildsEnchantment, tool)<=0) return;
                    world.setBlockState(pos, defaultstate);
                    if(crop.canGrow(world, world.random, pos, defaultstate)){
                        crop.grow(serverWorld, world.random, pos, defaultstate);
                    }
                }
            });
        }
    }


