package net.shadow.farmersmarket.components;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class RainDamageComponent implements Component, CommonTickingComponent, AutoSyncedComponent {
    public final PlayerEntity player;
public ItemStack stack;
    public RainDamageComponent(PlayerEntity player) {
        this.player = player;
    }
    public static int RAIN = 0;
    public static int DROWN = 0;

    public static int RAIN_MAX = 200;
    public static int DROWN_MAX = 5;
    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        RAIN = nbtCompound.getInt("rain");
        DROWN = nbtCompound.getInt("drown");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {

        nbtCompound.putInt("rain", RAIN);
        nbtCompound.putInt("drown", DROWN);
    }

    @Override
    public void tick() {
        if(player.isWet()){
            if(RAIN>=RAIN_MAX){
                RAIN = Math.max(RAIN - RAIN_MAX, 0);

                DROWN = Math.min(DROWN + 1, DROWN_MAX);
            }

            player.damage(player.getDamageSources().drown(), (DROWN));
            return;
        }

        if(RAIN>=1){
            RAIN--;

        }
        if(RAIN==0 && DROWN>0) {
            RAIN = RAIN_MAX;
            DROWN = Math.max(DROWN - 1, 0);
        }

    }

    public static void RainCounter(int value) {

        RAIN = Math.min(RAIN + value, RAIN_MAX);
        return;
    }

}
