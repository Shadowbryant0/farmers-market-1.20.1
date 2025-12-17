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
            if(RAIN>=RAIN_MAX){//when RAIN == 100
                RAIN = Math.max(RAIN - RAIN_MAX, 0);// it goes back down to 0

                DROWN = Math.min(DROWN + 1, DROWN_MAX);// and DROWN gets a +1
            }

            player.damage(player.getDamageSources().drown(), (DROWN));// for every stack of DROWN, you take damage in water
            return;
        }

        if(RAIN>=1){
            RAIN--;

        }
        //to remove drown, you need to be OUT of water, taking the pendant off does NOTHING
        if(RAIN==0 && DROWN>0) {
            RAIN = RAIN_MAX;
            DROWN = Math.max(DROWN - 1, 0);
        }

    }

    public static void RainCounter(int value) {

        RAIN = Math.min(RAIN + value, RAIN_MAX);//when you are wearing blaze or enderman, and in water/rain, this increases every tick (20 a second)
        return;
    }

}
