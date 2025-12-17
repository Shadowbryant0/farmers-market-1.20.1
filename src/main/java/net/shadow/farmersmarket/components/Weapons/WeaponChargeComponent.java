package net.shadow.farmersmarket.components.Weapons;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class WeaponChargeComponent implements Component, CommonTickingComponent, AutoSyncedComponent {
    public final PlayerEntity player;
public ItemStack stack;
    public WeaponChargeComponent(PlayerEntity player) {
        this.player = player;
    }
    public static int WYRM = 0;
    public static int FIRE = 0;
    public static int MAX_WYRM = 100;
    public static int FIFTH_WYRM = 20;

    public static int GREAT = 0;
    public static int MAX_GREAT = 150;
    public static int TWO_THREE_GREAT = 100;

    public static int SPADE = 0;
    public static int MAX_SPADE = 100;
    public static int HALF_SPADE = 50;

    public static int BLOOD = 0;
    public static int MAX_BLOOD = 100;
    public static int QUARTER_BLOOD = 25;

    public static int BEARDED = 0;
    public static int MAX_BEARDED = 100;

    public static int SICKLE = 0;
    public static int MAX_SICKLE = 100;
    public static int HALF_SICKLE = 50;

    public static int RAPIER = 0;
    public static int MAX_RAPIER = 100;
    public static int HALF_RAPIER = 50;
    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        WYRM = nbtCompound.getInt("wyrm");
        FIRE = nbtCompound.getInt("fire");
        SPADE = nbtCompound.getInt("spade");
        SICKLE = nbtCompound.getInt("sickle");
        RAPIER = nbtCompound.getInt("rapier");
        GREAT = nbtCompound.getInt("great");
        BLOOD = nbtCompound.getInt("blood");
        BEARDED = nbtCompound.getInt("bearded");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putInt("fire", FIRE);
        nbtCompound.putInt("wyrm", WYRM);
        nbtCompound.putInt("sickle", SICKLE);
        nbtCompound.putInt("spade", SPADE);
        nbtCompound.putInt("rapier", RAPIER);
        nbtCompound.putInt("great", GREAT);
        nbtCompound.putInt("blood", BLOOD);
        nbtCompound.putInt("bearded", BEARDED);

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
    public static void UseSPADE(int value){
        SPADE = Math.min(SPADE - value, MAX_SPADE);
    }
    public static void UseSICKLE(int value){
        SICKLE = Math.min(SICKLE - value, MAX_SICKLE);
    }
    public static void UseRapier(int value){
        RAPIER = Math.min(RAPIER - value, MAX_RAPIER);
    }
    public static void UseGREAT(int value){
        GREAT = Math.min(GREAT - value, MAX_GREAT);
    }
    public static void UseBLOOD(int value){
        BLOOD = Math.min(BLOOD - value, MAX_BLOOD);
    }
    public static void UseBEARDED(int value){
        BEARDED = Math.min(BEARDED - value, MAX_BEARDED);
    }

    public static void ChargeAll() {
        WYRM = MAX_WYRM;
        SPADE = MAX_SPADE;
        SICKLE = MAX_SICKLE;
        RAPIER = MAX_RAPIER;
        GREAT = MAX_GREAT;
        BLOOD = MAX_BLOOD;
        BEARDED = MAX_BEARDED;
    }
    public static void IncrementBEARDED(int value){
        BEARDED = Math.min(BEARDED + value, MAX_BEARDED);
    }
    public static void IncrementBLOOD(int value){
        BLOOD = Math.min(BLOOD + value, MAX_BLOOD);
    }
    public static void IncrementGREAT(int value){
        GREAT = Math.min(GREAT + value, MAX_GREAT);
    }
    public static void IncrementRapier(int value){
        RAPIER = Math.min(RAPIER + value, MAX_RAPIER);
    }
    public static void IncrementSICKLE(int value){
        SICKLE = Math.min(SICKLE + value, MAX_SICKLE);
    }
    public static void IncrementSPADE(int value){
        SPADE = Math.min(SPADE + value, MAX_SPADE);
    }
    public static void IncrementWYRM(int value){
        WYRM = Math.min(WYRM + value, MAX_WYRM);
    }
}
