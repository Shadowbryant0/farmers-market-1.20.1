package net.shadow.farmersmarket.mixin.aquiifermixins;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.shadow.farmersmarket.components.Weapons.BowChargeComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class DrownDamageMixin {
	@Inject(at = @At("HEAD"), method = "modifyAppliedDamage", cancellable = true)
	private void init(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        if(BowChargeComponent.DROWN) {
            if (source.isIn(DamageTypeTags.IS_DROWNING)) {
                cir.setReturnValue(amount);
            }
            //but how is the warcrime bow? you like it? oh you didnt even notice the fancy mechanic did you
        }
	}
}