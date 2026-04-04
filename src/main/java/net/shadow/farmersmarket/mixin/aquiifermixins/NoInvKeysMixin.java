package net.shadow.farmersmarket.mixin.aquiifermixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.shadow.farmersmarket.util.FarmersmarketUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(KeyBinding.class)
public class NoInvKeysMixin {
    @Shadow
    @Final
    public static String INVENTORY_CATEGORY;
    @Shadow
    @Final
    private String category;

    @ModifyReturnValue(method = "wasPressed", at = @At("RETURN"))
	private boolean NoInvKeybinds(boolean original) {
        if(MinecraftClient.getInstance().getCameraEntity() instanceof PlayerEntity player){
            if(FarmersmarketUtil.hasEffect(StatusEffects.LEVITATION, player)){
                if(this.category.equals(INVENTORY_CATEGORY)){
                    return false;
                }

            }
        }
        return original;
    }
}