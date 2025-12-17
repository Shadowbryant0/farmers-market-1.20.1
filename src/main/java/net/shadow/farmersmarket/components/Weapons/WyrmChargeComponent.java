package net.shadow.farmersmarket.components.Weapons;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class WyrmChargeComponent implements Component, CommonTickingComponent, AutoSyncedComponent {
    public final PlayerEntity player;
public ItemStack stack;
    public WyrmChargeComponent(PlayerEntity player) {
        this.player = player;
    }
    public static int WYRM = 0;
    public static int FIRE = 0;
    public static int MAX_WYRM = 100;
    public static int FIFTH_WYRM = 20;


    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        WYRM = nbtCompound.getInt("wyrm");
        FIRE = nbtCompound.getInt("fire");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putInt("fire", FIRE);
    }

    @Override
    public void tick() {
        if(player.getFireTicks() >0|| player.isInLava()){
            FIRE = 200;
        }else {
            if(FIRE>=0){
                FIRE--;
            }
        }

    }
    public static void UseWYRM(int value){
        WYRM = Math.min(WYRM - value, MAX_WYRM);
    }

    public static void IncrementCharge(int value){
        WYRM = Math.min(WYRM + value, MAX_WYRM);
    }
}
