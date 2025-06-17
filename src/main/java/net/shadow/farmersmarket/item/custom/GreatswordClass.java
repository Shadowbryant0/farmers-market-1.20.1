package net.shadow.farmersmarket.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import net.shadow.farmersmarket.item.ModItems;

import java.util.Objects;
import java.util.Random;

public class GreatswordClass extends SwordItem {
    private static final int COOLDOWN_TICKS = 200;

    //  right click is fast short dash that slams into enemies (custom stun effect)
    public GreatswordClass(Item.Settings settings) {
        super(Greatmat.INSTANCE, 3, -2.6F, settings);
    }

    double boost = 2.0d;
    double nerf = 0.5d;
    double notRightDimensionDebuff = 1.0d;
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }




    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        //    return TypedActionResult.consume(itemStack);
        // }

        //@Override
        //public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        //    if (!world.isClient) {
        //        Vec3d lookingDirection = user.getRotationVec(1.0f);
         //       RegistryKey<DimensionType> overworld = DimensionTypes.OVERWORLD;
       //         user.setVelocity(
       //                 lookingDirection.x * boost * nerf,
        //                lookingDirection.y * boost,
        //                lookingDirection.z * boost * nerf
       //         );
         //       user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20, 1));
//

           //     user.velocityModified = true;
           //     user.setSwimming(false);
           // }
           // {
            //    user.getItemCooldownManager().set(ModItems.GREATSWORD, COOLDOWN_TICKS);
           //     user.getItemCooldownManager().set(ModItems.MAINSWORD, COOLDOWN_TICKS);
           // }


        //    return super.use(world, user, hand);
        return TypedActionResult.consume(itemStack);
        }


    public boolean isCritical(LivingEntity user) {
        return (user.getVelocity().getY() + 0.0784000015258789) <= 0;
    }
    public boolean CoolingDown(PlayerEntity user) {
            return (user.getItemCooldownManager().isCoolingDown(this));
        }
    public boolean Crouch(PlayerEntity user) {
        return (user.isSneaking());
    }





    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (isCritical(attacker) && CoolingDown((PlayerEntity) attacker)) {
            if (!Crouch((PlayerEntity) attacker)) {
                attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 20, 1));
                attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20, 1));
                attacker.setVelocity(
                        0,
                        1.5,
                        0
                );
            } else {
                attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 20, 1));
                attacker.setVelocity(
                        0,
                        0,
                        0
                );
            }
            attacker.velocityModified = true;
        }
        return super.postHit(stack, target, attacker);
    }
}

