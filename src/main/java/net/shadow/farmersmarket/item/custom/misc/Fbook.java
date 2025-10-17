package net.shadow.farmersmarket.item.custom.misc;

import net.minecraft.advancement.criterion.EnchantedItemCriterion;
import net.minecraft.advancement.criterion.ItemCriterion;
import net.minecraft.command.argument.ItemSlotArgumentType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ClickType;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.ModItems;

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
            Item item = itemStack.getItem();
            if (itemStack.getItem() instanceof AxeItem) {

                    if (EnchantmentHelper.getLevel(FarmersMarketEnchants.PrimalDesires, itemStack) == 0 && EnchantmentHelper.getLevel(FarmersMarketEnchants.Starvation, itemStack) == 0 && EnchantmentHelper.getLevel(FarmersMarketEnchants.JagerderSchuldigen, itemStack) == 0) {
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

                if (EnchantmentHelper.getLevel(FarmersMarketEnchants.HuntersLullabyEnchantment, itemStack) == 0 && EnchantmentHelper.getLevel(FarmersMarketEnchants.Devouring, itemStack) == 0) {
                    this.playInsertSound(player);
                    itemStack.addEnchantment(FarmersMarketEnchants.Devouring, 1);
                    stack.decrement(1);
                    player.damage(player.getDamageSources().wither(),6);
                }
            }

            if (EnchantmentTarget.ARMOR_FEET.isAcceptableItem(item)||EnchantmentTarget.ARMOR_CHEST.isAcceptableItem(item)) {




                    this.playInsertSound(player);
                    itemStack.addEnchantment(FarmersMarketEnchants.LavaWader, 1);

                    stack.decrement(1);

                    player.damage(player.getDamageSources().wither(),8);

            }
            if (itemStack.getItem() instanceof PickaxeItem || itemStack.getItem() instanceof ShovelItem) {

                if (EnchantmentHelper.getLevel(FarmersMarketEnchants.Forging, itemStack) == 0) {
                    this.playInsertSound(player);
                    itemStack.addEnchantment(FarmersMarketEnchants.Forging, 1);
                    stack.decrement(1);
                    player.damage(player.getDamageSources().wither(),6);
                }
            }
            if (itemStack.isOf(Items.STICK)) {

                //if (EnchantmentHelper.getLevel(FarmersMarketEnchants.HuntersLullabyEnchantment, itemStack) == 0) {
                this.playInsertSound(player);
                itemStack.addEnchantment(Enchantments.KNOCKBACK, 254);
                stack.decrement(1);
                player.damage(player.getDamageSources().wither(),6);
                //}
            }



            return true;
        }
    }

    private void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.ENTITY_SKELETON_CONVERTED_TO_STRAY, 0.8F, 0.8F + entity.getWorld().getRandom().nextFloat() * 0.4F);
    }

}

