package net.shadow.farmersmarket.components.expressions.divinity;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.shadow.farmersmarket.components.RainDamageComponent;
import net.shadow.farmersmarket.item.ModItems;
import org.spongepowered.asm.mixin.Unique;

public class Firstdivinity_flight implements Component, CommonTickingComponent, AutoSyncedComponent {
    public final PlayerEntity player;
public ItemStack stack;
    public Firstdivinity_flight(PlayerEntity player) {
        this.player = player;
    }

    public static boolean GROUNDED = false;
    @Override
    public void readFromNbt(NbtCompound nbtCompound) {

        GROUNDED = nbtCompound.getBoolean("grounded");
    }
    @Unique
    private void setAbilities(PlayerAbilities abilities, boolean firstdivinity) {
        if(GROUNDED) {
            abilities.allowFlying = false;
            abilities.flying = false;
        }
            abilities.allowFlying = firstdivinity;
        if(abilities.flying){
            if(!firstdivinity){
                abilities.flying = false;
            }
        }
    }
    @Override
    public void writeToNbt(NbtCompound nbtCompound) {

        nbtCompound.putBoolean("grounded", GROUNDED);
    }

    @Override
    public void tick() {
        if(!player.isCreative()&&!player.isSpectator()) {

            setAbilities(player.getAbilities(), (this.player.getOffHandStack().isOf(ModItems.FIRSTDIVINITY_FLIGHT)));
        }
    }


    public static void setGROUNDED(boolean grounded) {
        GROUNDED =  grounded;
    }

}
