package net.shadow.farmersmarket.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;

import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.ModItems;
import net.shadow.farmersmarket.util.FarmersmarketUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
class SliverOfFleshMixin {

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void onDeathInject(DamageSource source, CallbackInfo ci) {
        if (!(source.getAttacker() instanceof PlayerEntity attacker)) return;
        if (!(((Object) this) instanceof PlayerEntity victim)) return;

        ItemStack weapon = attacker.getMainHandStack();
        if (EnchantmentHelper.getLevel(FarmersMarketEnchants.HuntersLullabyEnchantment, weapon) > 0 || EnchantmentHelper.getLevel(FarmersMarketEnchants.PrimalDesires, weapon) > 0) {
            World world = attacker.getWorld();
            if (!world.isClient) {
                // Drop Silver Flesh
                ItemStack sliverFlesh = new ItemStack(ModItems.SLIVER_FLESH);
                Vec3d pos = victim.getPos();

                world.spawnEntity(new ItemEntity(world, pos.x, pos.y, pos.z, sliverFlesh));
            }
        }
    }
}
@Mixin(LivingEntity.class)
class StarvationMixin {
    @ModifyVariable(method = "damage", at = @At("HEAD"), argsOnly = true)
    private float FarmersMarket$Starvation(float value, DamageSource source) {

        if (source.getSource() instanceof LivingEntity living && !living.getWorld().isClient) {
            return value + FarmersmarketUtil.getBonusStarvationDamage(living, living.getMainHandStack());
        }
        return value;
    }
           // beserk - enchancement by MoriyaShiine
}
@Mixin(LivingEntity.class)
class StarvationKillMixin {

 @Inject(method = "onDeath", at = @At("HEAD"))
private void onDeathInject(DamageSource source, CallbackInfo ci) {
    if (!(source.getAttacker() instanceof LivingEntity attacker)) return;


    ItemStack weapon = attacker.getMainHandStack();
    if (EnchantmentHelper.getLevel(FarmersMarketEnchants.Starvation, weapon) > 0) {
        World world = attacker.getWorld();
        if (!world.isClient) {
            if (attacker instanceof PlayerEntity player){
                player.getHungerManager().add(8, .2f);
            } else {
                attacker.heal(6);
            }



      }
    }
    }
}