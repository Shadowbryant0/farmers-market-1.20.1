package net.shadow.farmersmarket.item.custom.misc;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ClickType;
import net.shadow.farmersmarket.ModConfigs;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.ModItems;

public class CSbook extends BookItem {


    public CSbook(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        if (clickType != ClickType.RIGHT) {
            return false;
        } else {
            ItemStack itemStack = slot.getStack();

            if (itemStack.getItem() instanceof ArmorItem) {

                    if (ModConfigs.calciumenabled) {
                        if (EnchantmentHelper.getLevel(Enchantments.PROTECTION, itemStack) == 0) {
                            this.playInsertSound(player);
                            itemStack.addEnchantment(FarmersMarketEnchants.CalciumInfused, 1);
                            stack.decrement(1);
                            player.damage(player.getDamageSources().wither(), 10);
                        }
                    }

            } else if (itemStack.isEmpty()) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 6000, 1));
                player.damage(player.getDamageSources().wither(), 14);
                stack.decrement(1);
            } else if (itemStack.isOf(ModItems.CSBOOK)) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 12000, 3));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 2000, 0));
                player.damage(player.getDamageSources().wither(), 19);
                stack.decrement(1);
                itemStack.decrement(1);

            }

            return true;
        }
    }

    private void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.ENTITY_SKELETON_CONVERTED_TO_STRAY, 0.8F, 0.8F + entity.getWorld().getRandom().nextFloat() * 0.4F);
    }

}

