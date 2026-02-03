package net.shadow.farmersmarket.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.shadow.farmersmarket.util.FMEnchantCheck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(HungerManager.class)
public class GluttonHungerMixin {
    @Shadow
    private int foodLevel;
    @Shadow
    private float saturationLevel;
    @Unique
    private boolean shouldDouble = false;

	@ModifyExpressionValue(at = @At(value = "CONSTANT", args = "intValue=20"), method = "add")
    private int ModifyHunger(int original){
        return shouldDouble ? original * 2 : original;
	}
    @ModifyExpressionValue(at = @At(value = "CONSTANT", args = "intValue=20"), method = "isNotFull")
    private int Modifyfull(int original){
        return shouldDouble ? original * 2 : original;
    }

    @Inject(at = @At(value = "HEAD"), method = "update")
    private void ModifyHunger(PlayerEntity player, CallbackInfo ci){
        shouldDouble = FMEnchantCheck.above0(FMEnchantCheck.getGluttony(player));
        if(!shouldDouble&& foodLevel>20){
            foodLevel = 20;
        }
        if(!shouldDouble&& saturationLevel>20){
            saturationLevel = 20;
        }
    }
}