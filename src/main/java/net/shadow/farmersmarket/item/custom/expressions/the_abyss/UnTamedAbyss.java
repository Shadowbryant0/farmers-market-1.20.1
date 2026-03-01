package net.shadow.farmersmarket.item.custom.expressions.the_abyss;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.ParrotEntity;
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

public class UnTamedAbyss extends Item {
    public UnTamedAbyss(Settings settings) {
        super(settings);
    }
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity living, Hand hand) {
        switch (living) {
            case IronGolemEntity ironGolem -> {
                user.getStackInHand(hand).setCount(0);
                living.dropStack(new ItemStack(ModItems.FIRSTOFTHEABYSS_SHACKLES));
                living.kill();
                user.sendMessage(Text.literal("The Abyss consumes the Golem, forming shackles from it's soul.").formatted(Formatting.DARK_RED), true);

            }
            case ParrotEntity parrot -> {
                user.getStackInHand(hand).setCount(0);
                living.dropStack(new ItemStack(ModItems.SECONDOFTHEABYSS_SONG));
                living.kill();
                user.sendMessage(Text.literal("The Abyss consumes the parrot, ripping it's songs from it's soul.").formatted(Formatting.DARK_RED), true);

            }
            case EndermanEntity enderman -> {
                user.getStackInHand(hand).setCount(0);
                living.dropStack(new ItemStack(ModItems.THIRDOFTHEABYSS_VOID));
                living.kill();
                user.sendMessage(Text.literal("The Abyss consumes the Empty husk, Personifying it's hunger to be more than a puppet.").formatted(Formatting.DARK_RED), true);

            }
            default -> {

            }
        }
        return super.useOnEntity(stack, user, living, hand);
    }
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.farmersmarket.abyss.tooltip"));
        tooltip.add(Text.translatable("tooltip.farmersmarket.abyss.tooltip2"));
        tooltip.add(Text.translatable("tooltip.farmersmarket.abyss.tooltip3"));
        tooltip.add(Text.translatable("tooltip.farmersmarket.abyss.tooltip4"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
