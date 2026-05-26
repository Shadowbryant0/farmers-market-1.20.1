package net.shadow.farmersmarket.enchantments.Armor.Boots;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;

public class SoftStep extends Enchantment {

    public SoftStep() {
        super(Rarity.RARE, EnchantmentTarget.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
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

