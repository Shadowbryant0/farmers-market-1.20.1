package net.shadow.farmersmarket.enchantments.Pickaxe;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;

public class Forging extends Enchantment {

    public Forging() {
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

            return 3F;
    }
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
    target.setFireTicks(200);
    }
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof PickaxeItem ||stack.getItem() instanceof ShovelItem;

    }


}
