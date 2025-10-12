package net.shadow.farmersmarket.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffects;
import net.shadow.farmersmarket.util.FMEnchantCheck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameOverlayRenderer.class)
public class FireRenderMixin {



		@Inject(method = "renderFireOverlay", at = @At("HEAD"), cancellable = true)
		private static void renderFireOverlay(MinecraftClient client, MatrixStack matrices, CallbackInfo ci) {
            assert client.player != null;
            if ((client.player.isInLava()&& (FMEnchantCheck.getLavaWader(client.player)>0)) || (client.player.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)&& (FMEnchantCheck.getLavaWader(client.player)>0))) {
				ci.cancel();
			}
		}
		/*
        based on code
        written by
        the lovely
        RatMaid
        sourced by
        AttemptedConnection
        on the rattiest gang discord server
        Thanks for the help!
        (sourced from Github)
        https://github.com/Ladysnake/Impaled
         */

}