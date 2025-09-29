package net.shadow.farmersmarket.enchantments.Armor.Leggings;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;

public class LavaWader extends Enchantment {

    public LavaWader() {
        super(Rarity.RARE, EnchantmentTarget.ARMOR_LEGS, new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
    }



    public int getProtectionAmount(int level, DamageSource source) {

        if (source.isIn(DamageTypeTags.IS_FIRE)) {
            return 17;
        }
        else
        {return 0;}
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
        return super.canAccept(other) && other != Enchantments.PROTECTION && other != Enchantments.FIRE_PROTECTION && other != Enchantments.BLAST_PROTECTION && other != Enchantments.PROJECTILE_PROTECTION;
    }


    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ArmorItem;

    }
}

