package net.shadow.farmersmarket.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.shadow.farmersmarket.components.BlindfoldComponent;
import net.shadow.farmersmarket.effects.ModEffects;
import net.shadow.farmersmarket.item.trinkets.BlindfoldOfSightItem;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class BlindSightMixin {
    @Shadow
    @Nullable
    public ClientPlayerEntity player;

    @Inject(at = @At(value = "HEAD"), method = "Lnet/minecraft/client/MinecraftClient;hasOutline(Lnet/minecraft/entity/Entity;)Z", cancellable = true)
	private void Blind(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if(!(entity instanceof ClientPlayerEntity)) {
            assert player != null;
            if(!(BlindfoldOfSightItem.isWearingTrinket(player))){
                return;
            }
            if(player.distanceTo(entity)<30) {
                cir.setReturnValue(true);
            }
        }
		// This code is injected into the start of MinecraftServer.loadWorld()V
    }
}