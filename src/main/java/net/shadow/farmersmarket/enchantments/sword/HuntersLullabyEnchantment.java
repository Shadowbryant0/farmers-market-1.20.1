package net.shadow.farmersmarket.enchantments.sword;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class HuntersLullabyEnchantment extends Enchantment {
    public HuntersLullabyEnchantment() {
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
        return true; // only from special loot tables
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return false;
    }


    public void onTargetDamaged(LivingEntity user, LivingEntity target, int level) {
        if (!target.getWorld().isClient && user != null && target instanceof LivingEntity) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 0));
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 80, 0));
        }
        super.onTargetDamaged(user, target, level);
    }


    public float getAttackDamage(int level, net.minecraft.entity.attribute.EntityAttribute attribute) {
        return 1.5f * level; // small bonus damage
    }
}