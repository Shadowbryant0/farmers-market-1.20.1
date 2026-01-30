package net.shadow.farmersmarket.item.custom.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.shadow.farmersmarket.components.Weapons.ParryComponent;

public class CrystalineMirror extends Item {

    private static final int BUFF_INTERVAL = 200; // 20 ticks = 1 second
    private static final int BUFF_DURATION = 220;   // durability restored per interval
    public CrystalineMirror(Settings settings) {
        super(settings);
    }@Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient || !(entity instanceof ServerPlayerEntity player)) {
            return;
        }

        // Use tick counter stored in NBT
        int tickCounter = stack.getOrCreateNbt().getInt("buff_cooldown");
        tickCounter++;
        if (tickCounter >= BUFF_INTERVAL) {
            tickCounter = 0;
            if(player.getMainHandStack().getItem() instanceof SwordItem){
                player.addStatusEffect( new StatusEffectInstance(StatusEffects.STRENGTH, BUFF_DURATION/2, 1));
            }else {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, BUFF_DURATION, 1));
            }

        }

        stack.getOrCreateNbt().putInt("buff_cooldown", tickCounter);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }
    @Override
    public int getItemBarStep(ItemStack stack) {

        int tickCounter = stack.getOrCreateNbt().getInt("buff_cooldown");
        return Math.round((float)  tickCounter/ BUFF_INTERVAL * 13); // full bar = max charge
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return (253 << 16) | (255 << 8) | 168; // RGB mix
    }
    @Override
    public boolean hasGlint(ItemStack stack) {
        // Optional: make it always glow like it's enchanted
        return true;
    }

    @Override
    public boolean allowNbtUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }
}
