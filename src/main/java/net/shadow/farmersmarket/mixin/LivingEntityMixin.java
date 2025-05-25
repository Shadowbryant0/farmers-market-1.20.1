package net.shadow.farmersmarket.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;

import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void onDeathInject(DamageSource source, CallbackInfo ci) {
        if (!(source.getAttacker() instanceof PlayerEntity attacker)) return;
        if (!(((Object)this) instanceof PlayerEntity victim)) return;

        ItemStack weapon = attacker.getMainHandStack();
        if (EnchantmentHelper.getLevel(FarmersMarketEnchants.HuntersLullabyEnchantment, weapon) > 0) {
            World world = attacker.getWorld();
            if (!world.isClient) {
                // Drop Silver Flesh
                ItemStack silverFlesh = new ItemStack(ModItems.SILVER_FLESH);
                Vec3d pos = ((PlayerEntity) (Object) this).getPos();

                world.spawnEntity(new ItemEntity(world, pos.x, pos.y, pos.z, silverFlesh));
            }
        }
    }
}
