package net.shadow.farmersmarket.mixin.expressions.divinity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.shadow.farmersmarket.entity.SecondAbyss;
import net.shadow.farmersmarket.item.ModItems;
import net.shadow.farmersmarket.util.FarmersMarketEntityTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public abstract class SecondDivinityMixin extends LivingEntity implements SecondAbyss {


    protected SecondDivinityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "setTarget", cancellable = true)
    private void init(LivingEntity target, CallbackInfo ci) {
        if(target != null && target.getOffHandStack().getItem() == ModItems.SECONDDIVINITY_RADIENT_LIGHT){
            if(this.getSummoner() != null)
                return;
            if(!(this.getAttacker() == target)) {
                if(distanceTo(target)<20){
                    if(this.isUndead()) {
                        this.setOnFireFor(20);
                    }
                }
                ci.cancel();
            }
        }
        // This code is injected into the start of MinecraftServer.loadWorld()V
    }
}
