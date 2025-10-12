package net.shadow.farmersmarket.util;

import net.minecraft.enchantment.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;

public class FMEnchantCheck extends EnchantmentHelper {
    public static int getLavaWader(LivingEntity entity) {
        return getEquipmentLevel(FarmersMarketEnchants.LavaWader, entity);
    }
    public static int getWyrmStride(ItemStack itemStack) {
        return getLevel(FarmersMarketEnchants.WyrmStride, itemStack);
    }
}