package net.shadow.farmersmarket.enchantments.Axe;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;

public class PrimalDesires extends Enchantment{
    public PrimalDesires() {
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
        return true;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return true;
    }
    


    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof AxeItem;

    }
    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(!user.getWorld().isClient() && target instanceof LivingEntity livingEntity) {
            int b = user.getRandom().nextInt(20);
            switch (b) {
                case 19, 20->{
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 0));
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 80, 0));
                    break;
                }
                case 14, 15, 16, 17, 18 -> {
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 80, 0));
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 60, 0));
                    break;
                }
                case 10, 11, 12, 13 -> {
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 60, 0));
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 40, 0));
                    break;
                }
                case 1, 2, 3 -> {
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 0));
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60, 0));
                    break;
                }
                default -> {
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 40, 0));
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20, 0));

                    break;
                }
            }
        }
    }
}
