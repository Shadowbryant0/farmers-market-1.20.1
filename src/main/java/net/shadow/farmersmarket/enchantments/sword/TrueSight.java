package net.shadow.farmersmarket.enchantments.sword;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.shadow.farmersmarket.item.custom.weapons.PirateSaberItem;
import net.shadow.farmersmarket.item.custom.weapons.RapierWeaponItem;

public class TrueSight extends Enchantment {

    public TrueSight() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
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

    public float getAttackDamage(int level, EntityGroup group) {

            return 0.5F;
    }
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof PirateSaberItem;

    }
}
