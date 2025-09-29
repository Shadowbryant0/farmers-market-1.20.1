package net.shadow.farmersmarket.util;

import net.minecraft.enchantment.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.ModItems;

import java.util.List;

public class LavaWaderCheck extends EnchantmentHelper {
    public static int getLavaWader(LivingEntity entity) {
        return getEquipmentLevel(FarmersMarketEnchants.LavaWader, entity);
    }
}