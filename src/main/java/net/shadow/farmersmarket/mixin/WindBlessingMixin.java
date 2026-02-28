package net.shadow.farmersmarket.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.util.FMEnchantCheck;
import net.shadow.farmersmarket.util.FarmersMarketEntityTags;
import org.spongepowered.asm.mixin.Final;
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

    @Shadow
    public abstract boolean isBaby();

    @Shadow
    public abstract Identifier getLootTable();

    private static final Identifier ZOMBIE_DROPS_ID =
            new Identifier("minecraft", "entities/zombie");
    public WindBlessingMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At(value = "HEAD"), method = "getMovementSpeed()F", cancellable = true)
	private void speed(CallbackInfoReturnable<Float> cir) {
        if(this.isBaby() && this.getType().isIn(FarmersMarketEntityTags.ZOMBIES)){
            cir.setReturnValue((float) (movementSpeed +(movementSpeed*(EnchantmentHelper.getLevel(FarmersMarketEnchants.WINDBLESSING, this.getEquippedStack(EquipmentSlot.LEGS))*.4))));
            return;
        }
       cir.setReturnValue((float) (movementSpeed +(movementSpeed*(EnchantmentHelper.getLevel(FarmersMarketEnchants.WINDBLESSING, this.getEquippedStack(EquipmentSlot.LEGS))*.3))));
    }
}
@Mixin(PlayerEntity.class)
abstract class WindBlessingPlayerMixin extends LivingEntity{

    @Shadow
    public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @Shadow
    protected HungerManager hungerManager;

    @Shadow
    @Final
    private PlayerAbilities abilities;

    protected WindBlessingPlayerMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At(value = "HEAD"), method = "getMovementSpeed()F", cancellable = true)
    private void speed(CallbackInfoReturnable<Float> cir) {

        cir.setReturnValue((float) (this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)+(this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)*(FMEnchantCheck.getWind(this)*.2))));
    }
    @Inject(at = @At(value = "HEAD"), method = "addExhaustion")
    private void hunger(float exhaustion, CallbackInfo ci) {
        if(FMEnchantCheck.getWind(this)>0&&(!this.abilities.invulnerable)){
            exhaustion = (float) (exhaustion+(exhaustion*(FMEnchantCheck.getWind(this)*.15)));
            this.hungerManager.addExhaustion(exhaustion);
        }
    }
}