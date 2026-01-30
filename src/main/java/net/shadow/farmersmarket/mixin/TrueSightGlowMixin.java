package net.shadow.farmersmarket.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.shadow.farmersmarket.effects.FmEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class TrueSightGlowMixin {
    @Shadow
    public abstract boolean hasStatusEffect(StatusEffect effect);


    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/entity/LivingEntity;isGlowing()Z", cancellable = true)
	private void init(CallbackInfoReturnable<Boolean> cir) {
        if(this.hasStatusEffect(FmEffects.TRUESIGHT)){
            cir.setReturnValue(true);
            cir.cancel();
        }
		// This code is injected into the start of MinecraftServer.loadWorld()V
	}
}