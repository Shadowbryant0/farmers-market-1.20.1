package net.shadow.farmersmarket.item.custom.expressions.divinity;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.shadow.farmersmarket.components.Weapons.WeaponChargeComponent;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.entity.SecondAbyss;
import net.shadow.farmersmarket.item.ModItems;
import net.shadow.farmersmarket.item.custom.weapons.reskins.FrostSkin;
import net.shadow.farmersmarket.mixin.expressions.the_void.SecondAbyssMixin;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class UnRefinedDivinity extends Item {
    public UnRefinedDivinity(Settings settings) {
        super(settings);
    }
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity living, Hand hand) {
        switch (living) {
            case ChickenEntity chickenEntity -> {
                user.getStackInHand(hand).setCount(0);
                living.dropStack(new ItemStack(ModItems.FIRSTDIVINITY_FLIGHT));
                user.sendMessage(Text.literal("The Divinity reshapes itself using the chicken's wings.").formatted(Formatting.WHITE), true);
            }
            case WardenEntity warden -> {
                user.getStackInHand(hand).setCount(0);
                living.dropStack(new ItemStack(ModItems.THIRDIVINITY_GUARDIAN));
                living.kill();
                user.sendMessage(Text.literal("The Divinity reshapes the warden's heart, purifying the tortured souls inside.").formatted(Formatting.WHITE), true);

            }
            default -> {

            }
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
