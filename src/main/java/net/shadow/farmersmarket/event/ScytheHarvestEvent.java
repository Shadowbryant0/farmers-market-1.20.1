package net.shadow.farmersmarket.event;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import net.shadow.farmersmarket.item.ModItems;


public class ScytheHarvestEvent {


        public static void register() {
            PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
                if (!(world instanceof ServerWorld serverWorld)) return;

                ItemStack tool = player.getMainHandStack();


                    if (!tool.isSuitableFor(state)) return;

                    Direction facing = player.getHorizontalFacing(); // To determine orientation
                    boolean isVertical = player.getPitch() < -60 || player.getPitch() > 60;

                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            for (int dz = -1; dz <= 1; dz++) {

                                if (!(dx == 0 && dy == 0 && dz == 0)) {
                                    BlockPos newPos = pos.add(dx, 0, dz);
                                    BlockState targetState = world.getBlockState(newPos);
                                    if (targetState.isOf(Blocks.STONE) || targetState.isOf(Blocks.DIRT) || targetState.isOf(Blocks.FARMLAND) || targetState.isOf(Blocks.WATER) || targetState.isOf(Blocks.OAK_LOG) || targetState.isOf(Blocks.DARK_OAK_LOG) || targetState.isOf(Blocks.SPRUCE_LOG) || targetState.isOf(Blocks.BIRCH_LOG) || targetState.isOf(Blocks.ACACIA_LOG)) continue;


                                    if (tool.isOf(ModItems.GAYSCYTHE) && tool.isSuitableFor(targetState)) {
                                        serverWorld.breakBlock(newPos, true, player);
                                    }
                                }
                            }
                        }
                    }

            });
        }
    }


