package net.shadow.farmersmarket.item.components.Weapons;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class GreatSwordChargeComponent implements Component, CommonTickingComponent, AutoSyncedComponent {
    public final PlayerEntity player;
public ItemStack stack;
    public GreatSwordChargeComponent(PlayerEntity player) {
        this.player = player;
    }
    public static int GREAT = 0;
    public static int MAX_GREAT = 150;
    public static int TWO_THREE_GREAT = 100;


    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        GREAT = nbtCompound.getInt("great");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putInt("great", GREAT);
    }

    @Override
    public void tick() {

    }
    public static void UseAltGREAT(){
        GREAT = Math.min(GREAT - MAX_GREAT, MAX_GREAT);
    }
    public static void UseGREAT(){
        GREAT = Math.min(GREAT - TWO_THREE_GREAT, MAX_GREAT);
    }

    public static void IncrementCharge(){
        GREAT = Math.min(GREAT + 10, MAX_GREAT);
    }
}
