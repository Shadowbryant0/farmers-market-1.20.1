package net.shadow.farmersmarket.components.expressions.the_abyss;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class SecondAbyss_Song implements Component, CommonTickingComponent, AutoSyncedComponent {
    public final LivingEntity player;
public ItemStack stack;
    public SecondAbyss_Song(PlayerEntity player) {
        this.player = player;
    }

    public static int SUMMON = 0;
    @Override
    public void readFromNbt(NbtCompound nbtCompound) {

        SUMMON = nbtCompound.getInt("summon");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {

        nbtCompound.putInt("summon", SUMMON);
    }

    @Override
    public void tick() {
        if(SUMMON>0){
            SUMMON--;
        }
    }

    public static void Summon() {
        SUMMON =  3400;
    }
    public static boolean offCooldown() {
        return (SUMMON<=0);
    }
}
