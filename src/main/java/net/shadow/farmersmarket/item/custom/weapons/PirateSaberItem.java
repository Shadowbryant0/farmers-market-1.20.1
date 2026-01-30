package net.shadow.farmersmarket.item.custom.weapons;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.shadow.farmersmarket.components.Weapons.ParryComponent;
import net.shadow.farmersmarket.effects.FmEffects;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.materials.WeaponMaterials;

public class PirateSaberItem extends SwordItem {

    private static final int COOLDOWN_TICKS = 40;
    public PirateSaberItem(Settings settings) {

        super(WeaponMaterials.SABER, 4, -2.3F, settings);
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack stack = user.getStackInHand(hand);
        if (EnchantmentHelper.getLevel(FarmersMarketEnchants.TRUESIGHT, stack) == 0) {
            ParryComponent.ParryAction();
        }else{
            ParryComponent.TrueSightAction();

        }
        user.getItemCooldownManager().set(this, (COOLDOWN_TICKS));
        return super.use(world, user, hand);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }
    @Override
    public int getItemBarStep(ItemStack stack) {
        if(ParryComponent.PARRY_ULTRA>0) {
            return Math.round((float) ParryComponent.PARRY_ULTRA / ParryComponent.PARRY_MAX * 13); // full bar = max charge
        }else if(ParryComponent.PARRY>0) {
                return Math.round((float) ParryComponent.PARRY / ParryComponent.PARRY_MAX * 13); // full bar = max charge
        }else{
            return Math.round((float) ParryComponent.PARRY_CHARGE / ParryComponent.PARRY_CHARGE_MAX   * 13); // full bar = max charge
        }
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        if(ParryComponent.PARRY_ULTRA >0) {
            return (200 << 16) | (50 << 8) | 200; // RGB mix
        }else{
            return (0 << 16) | (146 << 8) | 255; // RGB mix
        }
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(ParryComponent.TRUESIGHT) {
            target.addStatusEffect(new StatusEffectInstance(FmEffects.TRUESIGHT, 400, 0));
            ParryComponent.TRUESIGHT = false;
            ParryComponent.PARRY_CHARGE = 0;
        }


        return super.postHit(stack, target, attacker);
    }
}
