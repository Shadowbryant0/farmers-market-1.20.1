package net.shadow.farmersmarket.components;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.shadow.farmersmarket.item.ModItems;

public class BlazeIdolComponent implements Component, CommonTickingComponent, AutoSyncedComponent {
    public final PlayerEntity player;
public ItemStack stack;
    public BlazeIdolComponent(PlayerEntity player) {
        this.player = player;
    }
public static int BLAZE = 0;
    public static int FIRE = 0;

    public static int FIRE_MAX = 20;
    public static int BLAZE_MAX = 20;
    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        BLAZE = nbtCompound.getInt("blaze");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {

        nbtCompound.putInt("blaze", BLAZE);
    }

    @Override
    public void tick() {
        if (this.player.getOffHandStack().isOf(ModItems.BLAZEIDOL)) {
            if(player.isWet()){
                RainDamageComponent.RainCounter(1);
            }
            if(player.isOnFire()|| player.isInLava()) {
                FIRE = Math.min(FIRE +1, FIRE_MAX);
            }else {
                FIRE = Math.max(FIRE - 1, 0);
            }

            if(FIRE>0) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 40, 0));
            }
            if(BLAZE<=19){
                BLAZE++;
            }
            return;
        }

        FIRE = Math.max(FIRE - 1, 0);
        BLAZE = Math.max(BLAZE - 1, 0);
    }



}
