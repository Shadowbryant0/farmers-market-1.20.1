package net.shadow.farmersmarket.item.custom.misc;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.shadow.farmersmarket.block.ModBlocks;

public class CloudBlockItem extends Item {
    public CloudBlockItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        BlockPos pos = user.getBlockPos().offset(Direction.Axis.Y, -1);
        if(world.getBlockState(pos) != Blocks.AIR.getDefaultState())return super.use(world, user, hand);
        world.setBlockState(pos, ModBlocks.CLOUD_BLOCK.getDefaultState());
        ItemStack stack = user.getStackInHand(hand);
        stack.decrement(1);
        return super.use(world, user, hand);
    }
}
