package net.shadow.farmersmarket.item.custom.misc;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.shadow.farmersmarket.item.ModItems;

import java.util.Map;
import java.util.stream.Collectors;

public class Cracked_egg extends Item {
    public Cracked_egg(Settings settings) {
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


            ItemStack newstack = new ItemStack(ModItems.EGG_EMBRYO);
            ItemStack eggbundle = new ItemStack(ModItems.EGG_BUNDLE);



            if(itemStack.isEmpty()) {


                stack.decrement(1);
                player.giveItemStack(eggbundle);

                slot.insertStack(newstack, 1);


                player.sendMessage(Text.literal("You feel a cold breeze rush out of the egg. what have you done"), true);

                return true;

            }
            return true;
        }

    }
}

