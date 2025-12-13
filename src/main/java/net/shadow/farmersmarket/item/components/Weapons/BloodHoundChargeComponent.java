package net.shadow.farmersmarket.item.components.Weapons;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class BloodHoundChargeComponent implements Component, CommonTickingComponent, AutoSyncedComponent {
    public final PlayerEntity player;
public ItemStack stack;
    public BloodHoundChargeComponent(PlayerEntity player) {
        this.player = player;
    }
    public static int BLOOD = 0;
    public static int MAX_BLOOD = 100;
    public static int QUARTER_BLOOD = 25;


    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        BLOOD = nbtCompound.getInt("blood");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putInt("blood", BLOOD);
    }

    @Override
    public void tick() {

    }
    public static void UseBLOOD(){
        BLOOD = Math.min(BLOOD - QUARTER_BLOOD, MAX_BLOOD);
    }
    public static void UseAltBLOOD(){
        BLOOD = Math.min(BLOOD - MAX_BLOOD, MAX_BLOOD);
    }

    public static void IncrementCharge(){
        BLOOD = Math.min(BLOOD + 10, MAX_BLOOD);
    }
}
