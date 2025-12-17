package net.shadow.farmersmarket.enchantments.unique;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.shadow.farmersmarket.item.custom.weapons.WyrmSpearItem;

public class WyrmsStride extends Enchantment {

    public WyrmsStride() {
        super(Rarity.RARE, EnchantmentTarget.TRIDENT, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
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


    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof WyrmSpearItem;

    }
}
