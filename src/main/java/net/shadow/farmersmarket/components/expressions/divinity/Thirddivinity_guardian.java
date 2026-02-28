package net.shadow.farmersmarket.components.expressions.divinity;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.shadow.farmersmarket.item.ModItems;
import org.spongepowered.asm.mixin.Unique;

import java.util.List;

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
