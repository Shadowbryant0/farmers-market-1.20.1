package net.shadow.farmersmarket.item.custom.expressions.divinity;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.shadow.farmersmarket.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UnRefinedDivinity extends Item {
    public UnRefinedDivinity(Settings settings) {
        super(settings);
    }
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity living, Hand hand) {
        if(living instanceof ChickenEntity){
                user.getStackInHand(hand).setCount(0);
                living.dropStack(new ItemStack(ModItems.FIRSTDIVINITY_FLIGHT));
                user.sendMessage(Text.literal("The Divinity reshapes itself using the chicken's wings.").formatted(Formatting.WHITE), true);
            }
        if(living instanceof WardenEntity){
                user.getStackInHand(hand).setCount(0);
                living.dropStack(new ItemStack(ModItems.THIRDIVINITY_GUARDIAN));
                living.kill();
                user.sendMessage(Text.literal("The Divinity reshapes the warden's heart, purifying the tortured souls inside.").formatted(Formatting.WHITE), true);
        }
        return super.useOnEntity(stack, user, living, hand);
    }
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.farmersmarket.divinity.tooltip"));
        tooltip.add(Text.translatable("tooltip.farmersmarket.divinity.tooltip2"));
        tooltip.add(Text.translatable("tooltip.farmersmarket.divinity.tooltip3"));
        tooltip.add(Text.translatable("tooltip.farmersmarket.divinity.tooltip4"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
