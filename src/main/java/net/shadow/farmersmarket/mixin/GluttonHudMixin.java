package net.shadow.farmersmarket.mixin;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.shadow.farmersmarket.util.FMEnchantCheck;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class GluttonHudMixin {
    @Shadow
    protected abstract PlayerEntity getCameraPlayer();

    @Shadow
    private int scaledWidth;

    @Shadow
    private int scaledHeight;

    @Shadow
    @Final
    private static Identifier ICONS;

    @Shadow
    private int ticks;

    @Shadow
    @Final
    private Random random;

    @Shadow
    @Final
    private MinecraftClient client;

    @Unique
    private int getHungerRows(PlayerEntity Player) {
        return FMEnchantCheck.getGluttony(Player)+1;
    }
    @Inject(at = @At("HEAD"), method = "renderStatusBars")
	private void init(DrawContext context, CallbackInfo ci) {
        PlayerEntity player = this.getCameraPlayer();
        if(FMEnchantCheck.above0(FMEnchantCheck.getGluttony(player))){
            int ac;
            int ab;
            int aa;
            int z;
            int y;
            int x;
            HungerManager hungerManager = player.getHungerManager();
            int k = hungerManager.getFoodLevel();
            int n = this.scaledWidth / 2 + 91;
            int o = this.scaledHeight - 49;
            for (y = 10; y < 20; ++y) {
                z = o;
                aa = 16;
                ab = 0;
                ac = n - y * 8 + 70;
                if (player.hasStatusEffect(StatusEffects.HUNGER)) {
                    aa += 36;
                    ab = 13;
                }

                if (player.getHungerManager().getSaturationLevel() <= 0.0f && this.ticks % (k * 3 + 1) == 0) {
                    z += this.random.nextInt(3) - 1;
                }
            context.drawTexture(ICONS, ac, z, 16 + ab * 9, 27, 9, 9);
            if (y * 2 + 1 < k) {
                context.drawTexture(ICONS, ac, z, aa + 36, 27, 9, 9);
            }
            if (y * 2 + 1 != k) continue;
            context.drawTexture(ICONS, ac, z, aa + 45, 27, 9, 9);
            }
            if (FabricLoader.getInstance().isModLoaded("appleskin")){

            }
        }

		// This code is injected into the start of MinecraftServer.loadWorld()V
	}
}