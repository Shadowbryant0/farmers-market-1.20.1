package net.shadow.farmersmarket.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.shadow.farmersmarket.util.FMEnchantCheck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import squeek.appleskin.api.event.HUDOverlayEvent;
import squeek.appleskin.client.HUDOverlayHandler;

@Pseudo
@Mixin(HUDOverlayHandler.class)
public abstract class AppleSkinMixin {
    @Shadow
    protected abstract void drawSaturationOverlay(HUDOverlayEvent.Saturation event, MinecraftClient mc, float saturationGained, float alpha);
    @Unique
    private boolean shouldDouble = false;

    @Inject(at=@At("TAIL"), method = "onRender")
	private void init(DrawContext context, CallbackInfo ci) {

//        MinecraftClient mc = MinecraftClient.getInstance();
//        PlayerEntity player = mc.player;
//        assert player != null;
//
//        int top = mc.getWindow().getScaledHeight() - 49;
//        int right = mc.getWindow().getScaledWidth() / 2 + 91; // right of food bar
//
//        shouldDouble = FMEnchantCheck.above0(FMEnchantCheck.getGluttony(player));
//        HungerManager stats = player.getHungerManager();
//        HUDOverlayEvent.Saturation gluttonyRenderEvent = new HUDOverlayEvent.Saturation(stats.getSaturationLevel(), right, top, context);
//        drawSaturationOverlay(gluttonyRenderEvent, mc, 0, 1F);
//		// This code is injected into the start of MinecraftServer.loadWorld()V
	}
    @ModifyExpressionValue(at = @At(value = "CONSTANT", args = "floatValue=20"), method = "drawSaturationOverlay(Lnet/minecraft/client/gui/DrawContext;FFLnet/minecraft/client/MinecraftClient;IIF)V")
    private float ModifySat(float original){
        return shouldDouble ? original * 2 : original;
    }
}