package net.shadow.farmersmarket.item.custom.misc;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ClickType;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;

public class Fbook extends BookItem {


    public Fbook(Settings settings) {
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
            if (itemStack.getItem() instanceof AxeItem) {

                    if (EnchantmentHelper.getLevel(FarmersMarketEnchants.PrimalDesires, itemStack) == 0) {
                        this.playInsertSound(player);
                        int b = player.getRandom().nextInt(2);
                        if (b == 1) {
                            itemStack.addEnchantment(FarmersMarketEnchants.Starvation, 1);
                        }
                        if (b == 0){
                            itemStack.addEnchantment(FarmersMarketEnchants.JagerderSchuldigen, 1);
                        }

                        stack.decrement(1);
                        player.damage(player.getDamageSources().wither(),6);
                    }

            }
            if (itemStack.getItem() instanceof SwordItem) {

                    if (EnchantmentHelper.getLevel(FarmersMarketEnchants.HuntersLullabyEnchantment, itemStack) == 0) {
                        this.playInsertSound(player);
                        itemStack.addEnchantment(FarmersMarketEnchants.Devouring, 1);
                        stack.decrement(1);
                        player.damage(player.getDamageSources().wither(),6);
                    }
                }


            return true;
        }
    }

    private void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.ENTITY_SKELETON_CONVERTED_TO_STRAY, 0.8F, 0.8F + entity.getWorld().getRandom().nextFloat() * 0.4F);
    }

}

