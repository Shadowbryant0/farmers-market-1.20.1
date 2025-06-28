package net.shadow.farmersmarket.item.custom;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static net.minecraft.item.ToolMaterials.IRON;

public class FarmersScytheClass  extends MiningToolItem {


    public FarmersScytheClass(ToolMaterial material, int attackDamage, float attackSpeed, Item.Settings settings) {
        super(attackDamage, attackSpeed, material,BlockTags.HOE_MINEABLE, settings);
    }

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
                                BlockPos newPos = pos.add(dx, dy, dz);
                                BlockState targetState = world.getBlockState(newPos);


                                if (targetState.isOf(Blocks.NETHERRACK)) continue;

                                if (tool.isSuitableFor(targetState)) {
                                    serverWorld.breakBlock(newPos, true, player);
                                }
                            }
                        }
                    }
                }

        });
    }
}


