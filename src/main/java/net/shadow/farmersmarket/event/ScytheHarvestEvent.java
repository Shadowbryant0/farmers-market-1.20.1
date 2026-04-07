package net.shadow.farmersmarket.event;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.*;
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




                    Direction facing = player.getHorizontalFacing(); // To determine orientation
                    boolean isVertical = player.getPitch() < -60 || player.getPitch() > 60;
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            for (int dz = -1; dz <= 1; dz++) {
                                if (!(dx == 0 && dy == 0 && dz == 0)) {
                                    BlockPos newPos = pos.add(dx, 0, dz);
                                    BlockState targetState = world.getBlockState(newPos);
                                    if(targetState.getBlock() instanceof FernBlock){
                                        serverWorld.breakBlock(newPos, true, player);
                                    }
                                    if(targetState.getBlock() instanceof FlowerbedBlock){
                                        serverWorld.breakBlock(newPos, true, player);
                                    }
                                    if(targetState.getBlock() instanceof FlowerBlock){
                                        serverWorld.breakBlock(newPos, true, player);
                                    }
                                    if(targetState.getBlock() instanceof DeadBushBlock){
                                        serverWorld.breakBlock(newPos, true, player);
                                    }
                                    if(targetState.getBlock() instanceof TallPlantBlock){
                                        serverWorld.breakBlock(newPos, true, player);
                                    }
                                    if(!(targetState.getBlock() instanceof CropBlock crop)) continue;
                                    if (!tool.isSuitableFor(state)) return;
                                    if(crop.getAge(targetState) == crop.getMaxAge()) {
                                        if (tool.isOf(ModItems.GAYSCYTHE) && tool.isSuitableFor(targetState)) {
                                            serverWorld.breakBlock(newPos, true, player);
                                            world.setBlockState(newPos, crop.getDefaultState());
                                        }
                                    }
                                }
                            }
                        }
                    }

                if(!(state.getBlock() instanceof CropBlock crop)) return;
                if(crop.getAge(state) == crop.getMaxAge()) {
                    if (tool.isOf(ModItems.GAYSCYTHE) && tool.isSuitableFor(state)) {
                        serverWorld.breakBlock(pos, true, player);
                        world.setBlockState(pos, crop.getDefaultState());
                    }
                }

            });
        }
    }


