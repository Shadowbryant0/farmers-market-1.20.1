package net.shadow.farmersmarket.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.shadow.farmersmarket.item.ModItems;

import java.util.Arrays;
import java.util.List;

public class CustomAngelBlock extends Block {
    public CustomAngelBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockPos PlayerPos = player.getBlockPos().offset(Direction.Axis.Y, +1);
        world.setBlockState(pos, Blocks.AIR.getDefaultState());


        ItemStack cloud = new ItemStack(ModItems.CLOUD_ITEM);
        world.spawnEntity(new ItemEntity(world, PlayerPos.getX(), PlayerPos.getY(), PlayerPos.getZ(), cloud));
        return super.onUse(state, world, pos, player, hand, hit);
    }
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.handleFallDamage(fallDistance, 0, world.getDamageSources().fall());
    }
}
