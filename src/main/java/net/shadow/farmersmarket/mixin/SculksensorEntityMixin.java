package net.shadow.farmersmarket.mixin;

import net.minecraft.block.entity.SculkSensorBlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.event.GameEvent;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SculkSensorBlockEntity.VibrationCallback.class)
public abstract class SculksensorEntityMixin {
    @Shadow
    public abstract int getRange();

    @Shadow
    public abstract boolean triggersAvoidCriterion();

    @Inject(at = @At("HEAD"), method = "accept")
	private void init(ServerWorld world, BlockPos pos, GameEvent event, Entity sourceEntity, Entity entity, float distance, CallbackInfo ci) {
        if(entity instanceof LivingEntity living) {


            if ((living.distanceTo(sourceEntity) > (float) getRange() / 2) && EnchantmentHelper.getLevel(FarmersMarketEnchants.SOFTSTEP, living.getEquippedStack(EquipmentSlot.FEET)) > 0) {


                return;
            }
        }
		// This code is injected into the start of MinecraftServer.loadWorld()V
	}
}