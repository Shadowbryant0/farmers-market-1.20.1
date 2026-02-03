package net.shadow.farmersmarket.util;

import net.minecraft.enchantment.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;

public class FMEnchantCheck extends EnchantmentHelper {
    public static int getLavaWader(LivingEntity entity) {
        return getEquipmentLevel(FarmersMarketEnchants.LavaWader, entity);
    }
    public static int getAdapting(LivingEntity entity) {
        return getLevel(FarmersMarketEnchants.ADAPTABILITY, entity.getEquippedStack(EquipmentSlot.HEAD));
    }
    public static int getWyrmStride(ItemStack itemStack) {
        return getLevel(FarmersMarketEnchants.WyrmStride, itemStack);
    }
    public static int getGluttony(LivingEntity entity) {
        return getLevel(FarmersMarketEnchants.GLUTTONY, entity.getEquippedStack(EquipmentSlot.HEAD));
    }
    public static int getLavaWaderCollective(LivingEntity entity){
        int boots = getLevel(FarmersMarketEnchants.LavaWader, entity.getEquippedStack(EquipmentSlot.FEET));
        int chest = getLevel(FarmersMarketEnchants.LavaWader, entity.getEquippedStack(EquipmentSlot.CHEST));
        return (boots+chest);
    }
    public static boolean above0(int enchantment){
        return enchantment > 0;
    }
}