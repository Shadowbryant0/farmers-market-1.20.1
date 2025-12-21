package net.shadow.farmersmarket.components.Weapons;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class KnuckleDusterComponent implements Component, CommonTickingComponent, AutoSyncedComponent {
    public final PlayerEntity player;
public ItemStack stack;
    public KnuckleDusterComponent(PlayerEntity player) {
        this.player = player;
    }
    public static int MAIN = 0;
    public static int MAIN_SPECIAL = 0;
    public static int OFF = 0;
    public static int OFF_SPECIAL = 0;
    public static int MAX_OFF = 40;


    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        MAIN = nbtCompound.getInt("main");
        OFF = nbtCompound.getInt("off");
        MAIN_SPECIAL = nbtCompound.getInt("main_s");
        OFF_SPECIAL = nbtCompound.getInt("off_s");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putInt("main", MAIN);
        nbtCompound.putInt("off", OFF);
        nbtCompound.putInt("main_s", MAIN_SPECIAL);
        nbtCompound.putInt("off_s", OFF_SPECIAL);
    }

    @Override
    public void tick() {

        MAIN = Math.max(MAIN - 1, 0);
        MAIN_SPECIAL = Math.max(MAIN_SPECIAL - 1, 0);
        OFF = Math.max(OFF - 1, 0);
        OFF_SPECIAL = Math.max(OFF_SPECIAL - 1, 0);
    }
    public static void enableOffSwing(int value){
        OFF = Math.min(OFF + value, MAX_OFF);
    }
    public static void enableOffSpecial(int value){
        OFF_SPECIAL = Math.min(OFF_SPECIAL + value, MAX_OFF);
    }
    public static void enableMainSpecial(int value){
        MAIN_SPECIAL = Math.min(MAIN_SPECIAL + value, MAX_OFF);
    }
    public static void resetOffhand(){
        OFF = 0;
    }
    public static void resetOffSpecial(){
        OFF_SPECIAL = 0;
    }
    public static void resetMainSpecial(){
        MAIN_SPECIAL = 0;
    }
}
