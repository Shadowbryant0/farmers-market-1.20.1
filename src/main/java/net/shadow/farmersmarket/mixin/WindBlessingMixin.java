package net.shadow.farmersmarket.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.util.FMEnchantCheck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class WindBlessingMixin extends Entity {
    @Shadow
    private float movementSpeed;

    @Shadow
    public abstract ItemStack getEquippedStack(EquipmentSlot var1);

    public WindBlessingMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At(value = "HEAD"), method = "getMovementSpeed()F", cancellable = true)
	private void init(CallbackInfoReturnable<Float> cir) {
       cir.setReturnValue((float) (movementSpeed +(movementSpeed*(EnchantmentHelper.getLevel(FarmersMarketEnchants.WINDBLESSING, this.getEquippedStack(EquipmentSlot.LEGS))*.2))));
    }
}
@Mixin(PlayerEntity.class)
abstract class WindBlessingPlayerMixin extends LivingEntity{

    @Shadow
    public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    protected WindBlessingPlayerMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At(value = "HEAD"), method = "getMovementSpeed()F", cancellable = true)
    private void init(CallbackInfoReturnable<Float> cir) {
        if(FMEnchantCheck.getWind(this)>0){
            ItemStack itemStack = this.getEquippedStack(EquipmentSlot.LEGS);
            if(this.random.nextBetween(0, 48)==6) {
                itemStack.damage(1, this, player -> player.sendEquipmentBreakStatus(EquipmentSlot.LEGS));
            }
        }
        cir.setReturnValue((float) (this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)+(this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)*(FMEnchantCheck.getWind(this)*.2))));
    }
}