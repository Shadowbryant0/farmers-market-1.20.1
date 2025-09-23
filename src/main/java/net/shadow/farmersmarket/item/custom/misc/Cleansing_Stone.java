package net.shadow.farmersmarket.item.custom.misc;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.shadow.farmersmarket.ModConfigs;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.ModItems;

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

    @Override
    public int getEnchantability() {
        return 0;
    }

    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        if (!player.getItemCooldownManager().isCoolingDown(this)) {




            if (clickType != ClickType.RIGHT) {
                return false;
            }else{
                    ItemStack itemStack = slot.getStack();


                    ItemStack newstack = itemStack.copyWithCount(itemStack.getCount());


                    Map<Enchantment, Integer> map = (Map) EnchantmentHelper.get(itemStack).entrySet().stream().filter((entry) -> !((Enchantment) entry.getKey()).isCursed()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    EnchantmentHelper.set(map, newstack);
                    newstack.setRepairCost(0);



                    for (Map.Entry<Enchantment, Integer> entry : map.entrySet()) {
                        Enchantment enchantment = (Enchantment) entry.getKey();


    itemStack.decrement(1);
    stack.decrement(1);
    slot.insertStack(newstack, 1);
    newstack.addEnchantment(enchantment, (Integer) entry.getValue());
    player.heal(5);
    player.sendMessage(Text.literal("You feel relief wash over you."), true);
                        this.playInsertSound(player);
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0));
    {
        player.getItemCooldownManager().set(this, COOLDOWN_TICKS);
    }
    return true;



                    }
                if(!itemStack.isEnchantable()){
                    itemStack.decrement(1);
                    stack.decrement(1);
                    slot.insertStack(newstack, 1);
                    this.playInsertSound(player);
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0));
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
