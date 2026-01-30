package net.shadow.farmersmarket.components.Weapons;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class ParryComponent implements Component, CommonTickingComponent, AutoSyncedComponent {
    public final PlayerEntity player;
public ItemStack stack;
    public ParryComponent(PlayerEntity player) {
        this.player = player;
    }
    public static int PARRY = 0;
    public static Boolean TRUESIGHT = false;
    public static int PARRY_ULTRA = 0;
    public static int PARRY_CHARGE = 0;


    public static int PARRY_MAX = 20;
    public static int PARRY_CHARGE_MAX = 100;
    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        PARRY = nbtCompound.getInt("parry");
        TRUESIGHT = nbtCompound.getBoolean("truesight");
        PARRY_ULTRA = nbtCompound.getInt("parry_ultra");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {

        nbtCompound.putInt("parry", PARRY);
        nbtCompound.putBoolean("truesight", TRUESIGHT);
        nbtCompound.putInt("parry_ultra", PARRY_ULTRA);
    }

    @Override
    public void tick() {

        if(PARRY>0) {
            PARRY = Math.max(PARRY - 1, 0);
        }
        if(PARRY_ULTRA>0) {
            PARRY_ULTRA = Math.max(PARRY_ULTRA - 1, 0);
        }

    }
    public static void ParryAction(){if(
            PARRY_CHARGE==PARRY_CHARGE_MAX
    ){
        PARRY_CHARGE = 0;
        PARRY_ULTRA = 20;
    }else{
        PARRY = Math.min(PARRY + PARRY_MAX, PARRY_MAX);
        PARRY_CHARGE = Math.min(PARRY_CHARGE + 25, PARRY_CHARGE_MAX);

        }
    }
    public static void TrueSightAction(){if(
            PARRY_CHARGE==PARRY_CHARGE_MAX
    ){
        PARRY_CHARGE =0;
        TRUESIGHT = true;
    }else{
        PARRY = Math.min(PARRY + PARRY_MAX, PARRY_MAX);
        PARRY_CHARGE = Math.min(PARRY_CHARGE + 25, PARRY_CHARGE_MAX);

    }
    }
public static void Charge(){
        PARRY_CHARGE = PARRY_CHARGE_MAX;
        TRUESIGHT = true;
}

}
