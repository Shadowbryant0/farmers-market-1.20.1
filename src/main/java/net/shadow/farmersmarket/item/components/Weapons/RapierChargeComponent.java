package net.shadow.farmersmarket.item.components.Weapons;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class RapierChargeComponent implements Component, CommonTickingComponent, AutoSyncedComponent {
    public final PlayerEntity player;
public ItemStack stack;
    public RapierChargeComponent(PlayerEntity player) {
        this.player = player;
    }
    public static int RAPIER = 0;
    public static int MAX_RAPIER = 100;
    public static int HALF_RAPIER = 50;


    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        RAPIER = nbtCompound.getInt("rapier");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putInt("rapier", RAPIER);
    }

    @Override
    public void tick() {

    }
    public static void UseRapier(){
        RAPIER = Math.min(RAPIER - MAX_RAPIER, MAX_RAPIER);
    }
    public static void UseAltRapier(){
        RAPIER = Math.min(RAPIER - HALF_RAPIER, MAX_RAPIER);
    }

    public static void IncrementCharge(){
        RAPIER = Math.min(RAPIER + 10, MAX_RAPIER);
    }
}
