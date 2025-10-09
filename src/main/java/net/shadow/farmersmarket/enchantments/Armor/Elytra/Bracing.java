package net.shadow.farmersmarket.enchantments.Armor.Elytra;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;
import net.shadow.farmersmarket.util.FarmersMarketDamageTagsCustom;

public class Bracing extends Enchantment {

    public Bracing() {
        super(Rarity.RARE, EnchantmentTarget.WEARABLE, new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
    }



    public int getProtectionAmount(int level, DamageSource source) {
       if(source.isIn(FarmersMarketDamageTagsCustom.KINETIC)) {
           return 25;
       }
        return 0;
    }
    @Override
    public int getMinPower(int level) {
        return 10 + 10 * level;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 15;
    }

    @Override
    public boolean isTreasure() {
        return false; // only from special loot tables
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return false;
    }

    public boolean isCursed() {
        return true;
    }

    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other) && other != Enchantments.PROTECTION;
    }


    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ElytraItem;

    }
}

