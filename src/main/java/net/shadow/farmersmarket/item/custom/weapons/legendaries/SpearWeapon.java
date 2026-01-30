package net.shadow.farmersmarket.item.custom.weapons.legendaries;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.shadow.farmersmarket.components.Weapons.LegendaryChargeComponent;
import net.shadow.farmersmarket.components.Weapons.WeaponChargeComponent;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.custom.weapons.reskins.FrostSkin;

import java.util.List;

public class SpearWeapon extends Item {


    public SpearWeapon(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 100;
    }

    public static Vec3d getAmplifiedMovement(Entity entity) {
        if (!(entity instanceof PlayerEntity) && entity.hasVehicle()) {
            entity = entity.getRootVehicle();
        }

        return getKineticAttackMovement(entity).multiply(20.0);
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (!world.isClient) {
            if(LegendaryChargeComponent.SPEAR>= LegendaryChargeComponent.MAX_SPEAR){
                LegendaryChargeComponent.UseSPEAR();
            }
        }
        return super.use(world, user, hand);
    }
    public UseAction getUseAction(ItemStack stack) {

        if(LegendaryChargeComponent.SPEAR_A>0) {
            return UseAction.BRUSH;
        }
        if(LegendaryChargeComponent.SPEAR>=40) {
            return UseAction.BRUSH;
        }
        return UseAction.NONE;
    }
    public  static Vec3d getKineticAttackMovement(Entity entity) {
        LivingEntity var2 = entity.getControllingPassenger();
        if (var2 instanceof PlayerEntity playerEntity) {
            if (entity.isAlive()) {
                return getKineticAttackMovement(entity);
            }
        }
        return entity.getVelocity();
    }


    // === Durability bar as charge indicator ===
    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        if(LegendaryChargeComponent.SPEAR_A>0) {
            return Math.round((float) LegendaryChargeComponent.SPEAR_A / LegendaryChargeComponent.MAX_SPEAR_A * 13); // full bar = max charge
        }

        return Math.round((float) LegendaryChargeComponent.SPEAR / LegendaryChargeComponent.MAX_SPEAR * 13); // full bar = max charge
    }

    @Override
    public int getItemBarColor(ItemStack stack) {

            int red = (int) (255);
            int blue = (int) (0);
            int green = (int) (102);
            return (red << 16) | (green << 8) | blue; // RGB mix

    }
}
