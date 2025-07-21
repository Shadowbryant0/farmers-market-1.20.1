package net.shadow.farmersmarket.enchantments.Axe;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.shadow.farmersmarket.item.ModItems;
import net.minecraft.util.math.random.Random;

public class Devouring extends Enchantment {
    public Devouring() {
        super(Enchantment.Rarity.RARE, EnchantmentTarget.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
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
            int b = user.getRandom().nextInt(20);
            switch (b) {
                case 19, 20->{
                    if (user instanceof PlayerEntity player) {
                        player.getHungerManager().add(10, .3f);
                    } else {
                        user.heal(8);
                    }
                    break;
                }
                case 14, 15, 16, 17, 18 -> {
                    if (user instanceof PlayerEntity player) {
                        player.getHungerManager().add(6, .3f);
                    }
                    else {
                        user.heal(6);
                    }
                    break;
                }
                case 10, 11, 12, 13 -> {
                    if (user instanceof PlayerEntity player) {
                        player.getHungerManager().add(4, .2f);
                    } else {
                        user.heal(4);
                    }
                    break;
                }
                default -> {
                    if (user instanceof PlayerEntity player) {
                        player.getHungerManager().add(2, 0);
                    } else {
                        user.heal(2);
                    }
                    break;
                }
            }
        }
    }




    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof AxeItem;

    }

}
