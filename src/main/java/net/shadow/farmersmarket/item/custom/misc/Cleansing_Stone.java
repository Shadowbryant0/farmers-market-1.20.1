package net.shadow.farmersmarket.item.custom.misc;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;

import java.util.Map;
import java.util.stream.Collectors;

public class Cleansing_Stone extends Item{
    public Cleansing_Stone(Item.Settings settings) {
        super(settings);
    }
    private static final int COOLDOWN_TICKS = 60;

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        if (!player.getItemCooldownManager().isCoolingDown(this)) {




            if (clickType != ClickType.RIGHT) {
                return false;
            }else{
                    ItemStack itemStack = slot.getStack();


                    ItemStack newstack = itemStack.copyWithCount(itemStack.getCount());
                    if(itemStack.hasEnchantments()) {
                        Map<Enchantment, Integer> map =  EnchantmentHelper.get(itemStack).entrySet().stream().filter((entry) -> !(entry.getKey()).isCursed()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

                EnchantmentHelper.set(map, newstack);
                    newstack.setRepairCost(0);



                for(Map.Entry<Enchantment, Integer> entry : map.entrySet()) {
                    Enchantment enchantment = entry.getKey();

                    itemStack.decrement(1);
                    stack.decrement(1);
                    slot.insertStack(newstack, 1);
                    newstack.addEnchantment(enchantment,  entry.getValue());
                    player.heal(5);
                    player.sendMessage(Text.literal("You feel relief wash over you."), true);
                        this.playInsertSound(player);
                        {
                            player.getItemCooldownManager().set(this, COOLDOWN_TICKS);
                        }
                        return true;
                    }

                    }
                if(!itemStack.isEnchantable()){
                    itemStack.decrement(1);
                    stack.decrement(1);
                    slot.insertStack(newstack, 1);
                    this.playInsertSound(player);
                    return true;
                }



                }
            }
        return false;
    }

    private void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, 60F, 1.1F + entity.getWorld().getRandom().nextFloat() * 0.4F);
    }

}
