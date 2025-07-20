package net.shadow.farmersmarket.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)

public abstract class ForgingSpeedMixin extends LivingEntity{

 
    protected ForgingSpeedMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }




   // @Inject(method = "getBlockBreakingSpeed", at = @At("HEAD"))



    //void forgingMiningSpeedMixin(BlockState block, CallbackInfoReturnable<Float> cir){
    //    float f = this.getInventory().getBlockBreakingSpeed(block);
    //    ItemStack itemStack = this.getMainHandStack();
    //    if (EnchantmentHelper.getLevel(FarmersMarketEnchants.Forging, itemStack) > 0) {
    //         cir = 0.5f;
    //    }
   // }

    @Unique
    protected abstract PlayerEntity getInventory();


}