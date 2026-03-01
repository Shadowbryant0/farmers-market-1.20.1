package net.shadow.farmersmarket.mixin.expressions.divinity;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.shadow.farmersmarket.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BeaconBlock.class)
public class SecondDivinityObtainMixin {

    @Inject(method = "onUse", at = @At("HEAD"))
    private void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if (world.getBlockState(pos).isOf(Blocks.BEACON) && !world.isClient) {
            if (player.getStackInHand(hand).isOf(ModItems.UNREFINED_DIVINITY))
            {
                player.sendMessage(Text.literal("The Divinity creates a new vessel for the light").formatted(Formatting.WHITE), true);

                Block.dropStack(world,pos,new ItemStack(ModItems.SECONDDIVINITY_RADIENT_LIGHT.asItem(),1));
                world.breakBlock(pos, false);
                player.getMainHandStack().decrement(1);
            }
        }
    }
}
