package net.shadow.farmersmarket.mixin.aquiifermixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.shadow.farmersmarket.util.FarmersmarketUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class CancleMouseScrollingMixin {

    @Inject(method = "onMouseScroll", at =@At("HEAD"), cancellable = true)
    private static void ScrollInvStopper(long window, double horizontal, double vertical, CallbackInfo ci) {
        if(MinecraftClient.getInstance().cameraEntity instanceof PlayerEntity player){
            if(FarmersmarketUtil.hasEffect(StatusEffects.LEVITATION, player)){
                ci.cancel();
            }
        }
    }
}