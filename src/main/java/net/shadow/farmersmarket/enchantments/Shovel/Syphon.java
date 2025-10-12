package net.shadow.farmersmarket.enchantments.Shovel;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.shadow.farmersmarket.item.custom.weapons.AceofSpadesClass;

public class Syphon extends Enchantment {

    public Syphon() {
        super(Rarity.RARE, EnchantmentTarget.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
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
        if(group == EntityGroup.UNDEAD){
            return 0f;
        }
        return 1F;
    }
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof AceofSpadesClass;

    }


}
