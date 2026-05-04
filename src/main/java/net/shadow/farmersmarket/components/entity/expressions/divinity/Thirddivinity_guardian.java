package net.shadow.farmersmarket.components.entity.expressions.divinity;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class Thirddivinity_guardian implements Component, CommonTickingComponent, AutoSyncedComponent {
    public final LivingEntity livingEntity;
public ItemStack stack;
    public Thirddivinity_guardian(LivingEntity livingEntity) {
        this.livingEntity = livingEntity;
    }

    public static int REVIVE = 0;
    @Override
    public void readFromNbt(NbtCompound nbtCompound) {

        REVIVE = nbtCompound.getInt("revive");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {

        nbtCompound.putInt("revive", REVIVE);
    }

    @Override
    public void tick() {
        if(REVIVE>0){
            REVIVE--;
        }
    }

    public static void ChargeAll() {
        REVIVE =  6000;
    }
    public static void Death() {
        REVIVE =  40;
    }
}
