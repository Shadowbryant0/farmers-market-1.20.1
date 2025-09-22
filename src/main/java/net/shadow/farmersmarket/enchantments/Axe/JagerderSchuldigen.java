package net.shadow.farmersmarket.enchantments.Axe;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;

public class JagerderSchuldigen extends Enchantment{

    public JagerderSchuldigen() {
        super(Enchantment.Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
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

    public boolean isCursed() {
        return true;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return false;
    }

    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other) && other != FarmersMarketEnchants.Devouring && other != FarmersMarketEnchants.Starvation;
    }


    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof AxeItem;

    }

}
