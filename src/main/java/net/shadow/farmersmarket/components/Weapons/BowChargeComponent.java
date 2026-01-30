package net.shadow.farmersmarket.components.Weapons;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.shadow.farmersmarket.effects.FmEffects;

public class BowChargeComponent implements Component, CommonTickingComponent, AutoSyncedComponent {
    public final PlayerEntity player;
public ItemStack stack;
    public BowChargeComponent(PlayerEntity player) {
        this.player = player;
    }
    public static int RELOAD = 0;
    public static int MAX_RELOAD = 100;
    static int arrow = 0;
    public static int ARROW = 0;

    public static int ARROW_MAX = 20;
    public static boolean DROWN = false;


    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        RELOAD = nbtCompound.getInt("reload");
        ARROW = nbtCompound.getInt("arrows");
        DROWN = nbtCompound.getBoolean("drown");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putInt("reload", RELOAD);
        nbtCompound.putInt("arrows", ARROW);
        nbtCompound.putBoolean("drown", DROWN);
    }

    @Override
    public void tick() {
        DROWN = player.hasStatusEffect(FmEffects.SUFFICATION);

        if(RELOAD >= MAX_RELOAD) {
            RELOAD = Math.min(RELOAD - 100, 100);
            ARROW = Math.min(ARROW + 1, ARROW_MAX);

        }else {
            RELOAD++;
        }

    }
    public static void minusArrow(){
        ARROW = Math.max(ARROW - 1, 0);
    }

    public static int getItemBarStep() {
        return Math.round((float) arrow / ARROW_MAX * 13); // full bar = max charge
    }
}
