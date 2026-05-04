package net.shadow.farmersmarket.event;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.shadow.farmersmarket.components.ModWorldComponents;
import net.shadow.farmersmarket.components.world.BiteChopComponent;
import net.shadow.farmersmarket.item.custom.weapons.MultiTools.BarknBite.BiteAxe;

import java.util.ArrayList;
import java.util.*;
import java.util.List;

public class BiteChopEvent {

        public static void register() {
        PlayerBlockBreakEvents.BEFORE.register(new PlayerBlockBreakEvents.Before() {
            @Override
            public boolean beforeBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity
                    blockEntity) {

                ItemStack stack = player.getMainHandStack();
                if (stack.getItem() instanceof BiteAxe axe) {
                    if (axe.CHOP) {
                        if (state.isIn(BlockTags.LOGS)) {
                            List<BlockPos> tree = gatherTree(new ArrayList<>(), world, pos, state.getBlock());
                            if (tree.size() > 1 && tree.size() <= 256) {
                                ItemStack copy = stack.copy();
                                player.getMainHandStack().damage(tree.size(), player, stackUser -> {
                                    stackUser.setStackInHand(Hand.MAIN_HAND, copy);
                                });
                                tree.sort(Comparator.comparingInt(Vec3i::getY).reversed());
                                ModWorldComponents.LUMBERJACK.get(world).addTree(new BiteChopComponent.Tree(tree, pos));
                                return false;

                            }
                        }
                    }
                }
                return true;
            }

            private List<BlockPos> gatherTree(List<BlockPos> tree, World world, BlockPos pos, Block original) {
                if (tree.size() < 256) {
                    BlockPos.Mutable mutable = new BlockPos.Mutable();
                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            for (int z = -1; z <= 1; z++) {
                                BlockState state = world.getBlockState(mutable.set(pos.getX() + x, pos.getY() + y, pos.getZ() + z));
                                if (state.isIn(BlockTags.LOGS) && !tree.contains(mutable) && state.getBlock() == original) {
                                    tree.add(mutable.toImmutable());
                                    gatherTree(tree, world, mutable, original);
                                }
                            }
                        }
                    }
                }
                return tree;
            }
        });
    }
}
