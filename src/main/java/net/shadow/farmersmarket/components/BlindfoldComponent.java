package net.shadow.farmersmarket.components;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.shadow.farmersmarket.item.trinkets.BlindfoldOfSightItem;

public class BlindfoldComponent implements Component, CommonTickingComponent, AutoSyncedComponent {
    public final PlayerEntity player;
public ItemStack stack;
    public BlindfoldComponent(PlayerEntity player) {
        this.player = player;
    }
    public static int BLIND = 0;
    public static boolean BOOLIAN = false;

    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        BLIND = nbtCompound.getInt("blind");
        BOOLIAN = nbtCompound.getBoolean("boolian");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putInt("blind", BLIND);
        nbtCompound.putBoolean("boolian", BOOLIAN);
    }

    @Override
    public void tick() {

        if (BlindfoldOfSightItem.isWearingTrinket(player)) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 100, 3, false, false, false));

        }
    }



}
