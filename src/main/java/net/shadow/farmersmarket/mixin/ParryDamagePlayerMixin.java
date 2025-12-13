package net.shadow.farmersmarket.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.shadow.farmersmarket.item.components.Weapons.ParryComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class ParryDamagePlayerMixin{


    @Inject(
            at = @At("HEAD"), method = "isInvulnerableTo", cancellable = true)
    private void Parry(CallbackInfoReturnable<Boolean> cir) {
        if(ParryComponent.PARRY >0) {
            cir.setReturnValue(true);
        }
    }
}

