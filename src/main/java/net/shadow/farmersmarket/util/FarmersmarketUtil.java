package net.shadow.farmersmarket.util;


import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.item.*;



import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;

public class FarmersmarketUtil {


    public static boolean hasEnchantment(Enchantment enchantment, ItemStack stack) {
        System.out.println("enchant detected");
        return EnchantmentHelper.getLevel(enchantment, stack) > 0;
    }


    public static float getMaxBonusStarvationDamage(ItemStack stack) {
        float maxBonus = 2;
        for (EntityAttributeModifier modifier : stack.getAttributeModifiers(EquipmentSlot.MAINHAND).get(EntityAttributes.GENERIC_ATTACK_DAMAGE)) {
            maxBonus += (float) modifier.getValue();
        }
        return maxBonus / 2;
    }

    public static float getBonusStarvationDamage(LivingEntity living, ItemStack stack) {
        System.out.println("entity detected");
        if (living != null && hasEnchantment(FarmersMarketEnchants.Starvation, stack)) {
            if (living instanceof PlayerEntity player) {
                System.out.println("player detected");
                System.out.println(player.getHungerManager().getFoodLevel());
                float hunger = 18;
                float bonus = 0;
                while (hunger > player.getHungerManager().getFoodLevel()) {
                    hunger -= 2;
                    bonus += 1F;
                }
                return Math.min(bonus, getMaxBonusStarvationDamage(stack));

            }
            System.out.println("mob detected");
            float health = living.getMaxHealth() - 1;
            float bonus = 0;
            while (health > living.getHealth()) {
                health -= 2;
                bonus += 1F;
            }
            return Math.min(bonus, getMaxBonusStarvationDamage(stack));
        }
        return 0;
        }
    // beserk - enchancement by MoriyaShiine

    public static float JagerDamage(LivingEntity living, ItemStack stack) {

        if (living != null && hasEnchantment(FarmersMarketEnchants.JagerderSchuldigen, stack)) {

            float health = living.getMaxHealth() - 8;

            if (health > living.getHealth()) {

                return 6;
            }
            return 0;
        }
        return 0;
    }
    // beserk - enchancement by MoriyaShiine
    public static boolean isCritical(LivingEntity user) {
        return (user.getVelocity().getY()) <= 0;
    }
}



