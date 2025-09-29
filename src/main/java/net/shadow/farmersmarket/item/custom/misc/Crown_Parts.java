package net.shadow.farmersmarket.item.custom.misc;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.shadow.farmersmarket.item.ModItems;

public class Crown_Parts extends Item {
    public Crown_Parts(Settings settings) {
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

//    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
//
//
//        if (clickType != ClickType.RIGHT) {
//            return false;
//        } else {
//            ItemStack itemStack = slot.getStack();
//
//
//            ItemStack newstack = new ItemStack(ModItems.EGG_EMBRYO);
//            ItemStack eggbundle = new ItemStack(ModItems.EGG_BUNDLE2);
//
//
//
//            if(itemStack.isEmpty()) {
//
//
//                stack.decrement(1);
//                player.giveItemStack(eggbundle);
//
//                slot.insertStack(newstack, 1);
//
//
//
//                player.sendMessage(Text.literal("You feel the crown crumble apart"), true);
//
//                return true;
//
//            }
//            return true;
//        }
//
//    }
}

