package net.shadow.farmersmarket.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.util.FMEnchantCheck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FarmlandBlock.class)
public abstract class FarmlandMixin extends Block {
    public FarmlandMixin(Settings settings) {
        super(settings);
    }

    @Inject(at = @At("HEAD"), method = "onLandedUpon", cancellable = true)
	private void init(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance, CallbackInfo ci) {
		if(entity instanceof LivingEntity living) {
            if(EnchantmentHelper.getLevel(FarmersMarketEnchants.SOFTSTEP, living.getEquippedStack(EquipmentSlot.FEET))>0) {
                super.onLandedUpon(world, state, pos, entity, fallDistance);
                ci.cancel();
                return;
            }
        }
	}
}