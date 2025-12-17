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


    public static int PARRY_MAX = 20;
    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        PARRY = nbtCompound.getInt("parry");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {

        nbtCompound.putInt("parry", PARRY);
    }

    @Override
    public void tick() {

        if(PARRY>0) {
            PARRY = Math.min(PARRY - 1, PARRY_MAX);
        }

    }
    public static void ParryAction(){
        PARRY = Math.min(PARRY + PARRY_MAX, PARRY_MAX);
    }


}
