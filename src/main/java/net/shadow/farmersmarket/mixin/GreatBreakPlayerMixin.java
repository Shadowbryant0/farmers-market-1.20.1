package net.shadow.farmersmarket.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.shadow.farmersmarket.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
public abstract class GreatBreakPlayerMixin extends LivingEntity {


    protected GreatBreakPlayerMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }
    @WrapOperation(
            method = "disableShield",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/ItemCooldownManager;set(Lnet/minecraft/item/Item;I)V"))
    private void SwordBreak(ItemCooldownManager instance, Item item, int duration, Operation<Void> original) {
original.call(instance, item, duration);
        original.call(instance, ModItems.MAINSWORD, duration);
        original.call(instance, ModItems.GREATSWORD, duration);
        original.call(instance, ModItems.ALTSWORD, duration);

    }
}

