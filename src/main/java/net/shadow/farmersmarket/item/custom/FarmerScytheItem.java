package net.shadow.farmersmarket.item.custom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PlaceableOnWaterItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;

import static net.minecraft.item.ToolMaterials.IRON;

public class FarmerScytheItem extends FarmersScytheClass{
    public FarmerScytheItem(Item.Settings settings){super(IRON, 3, -2.4F, settings);}


}

