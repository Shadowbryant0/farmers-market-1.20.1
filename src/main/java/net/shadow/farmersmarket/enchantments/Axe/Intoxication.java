package net.shadow.farmersmarket.enchantments.Axe;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.shadow.farmersmarket.item.custom.ExecutionersAxeClass;

public class Intoxication extends Enchantment {
    public Intoxication() {
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
        return true;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(!user.getWorld().isClient() && target instanceof LivingEntity livingEntity) {


            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 60, 0));


        }
    }

    @Override
    public float getAttackDamage(int level, EntityGroup group) {
        return 0.5F; // small bonus damage
    }

    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ExecutionersAxeClass;

    }

}