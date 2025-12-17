package net.shadow.farmersmarket.item.custom.weapons;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.shadow.farmersmarket.components.Weapons.WeaponChargeComponent;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.custom.weapons.SweepingBase.SweepingShovelItem;
import net.shadow.farmersmarket.item.materials.ExcalatrowlMats;

public class AceofSpadesItem extends SweepingShovelItem {
    private static final int COOLDOWN_TICKS = 240;

    public AceofSpadesItem(Settings settings) {
        super(ExcalatrowlMats.INSTANCE, 1.5F, -2.5F, settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            ItemStack stack = user.getStackInHand(hand);
            if (EnchantmentHelper.getLevel(FarmersMarketEnchants.Syphon, stack) == 0) {
                return super.use(world, user, hand);
            } else {
                if(!user.isSneaking()) {
                    if (WeaponChargeComponent.SPADE >= WeaponChargeComponent.HALF_SPADE) {
                        WeaponChargeComponent.UseSPADE(50);
                        {
                            user.getItemCooldownManager().set(this, (COOLDOWN_TICKS / 2));
                        }
                        this.playInsertSound(world, user);
                        user.heal(5);
                        return super.use(world, user, hand);

                    }
                }
                if(user.isSneaking()){
                    if (WeaponChargeComponent.SPADE >= WeaponChargeComponent.MAX_SPADE) {
                        if (user.experienceLevel >= 4) {
                                user.addExperienceLevels(-4);
                                stack.setDamage(stack.getDamage() - 100);
                                WeaponChargeComponent.UseSPADE(100);
                        }
                    }
                }

                return super.use(world, user, hand);

            }
        }
        return super.use(world, user, hand);
    }
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (EnchantmentHelper.getLevel(FarmersMarketEnchants.Syphon, stack) != 0) {
            return super.useOnEntity(stack, user, entity, hand);
        } else {
            if (!user.getItemCooldownManager().isCoolingDown(this)) {


                {
                    user.getItemCooldownManager().set(this, COOLDOWN_TICKS);
                }


                if (WeaponChargeComponent.SPADE < WeaponChargeComponent.MAX_SPADE) {
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ(), 0, 0, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() + 1, 0, 0, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() + 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ(), 0, 0, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() - 1, 0, 0, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() - 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ(), 0, 0, user));

                    return super.useOnEntity(stack, user, entity, hand);
                } else {
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ(), 0, 0, user));

                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() + 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() + 1, 0, 0, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() + 1, 0, 0, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() - 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() + 1, 0, 0, user));

                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() + 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() - 1, 0, 0, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() - 1, 0, 0, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() - 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() - 1, 0, 0, user));

                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() + 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ(), 0, 0, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() - 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ(), 0, 0, user));

                    entity.setVelocity(0, 0, 0);

                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() + 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() + 1, 0, 8, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() + 1, 0, 8, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() - 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() + 1, 0, 8, user));

                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() + 2, 0, 8, user));

                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() + 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() - 1, 0, 8, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() - 1, 0, 8, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() - 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() - 1, 0, 8, user));

                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ() - 2, 0, 8, user));

                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() + 2, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ(), 0, 8, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() - 2, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ(), 0, 8, user));

                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() + 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ(), 0, 8, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(), entity.getBlockPos().toCenterPos().getX() - 1, entity.getBlockPos().getY(), entity.getBlockPos().toCenterPos().getZ(), 0, 8, user));

                    WeaponChargeComponent.UseSPADE(100);
                    return super.useOnEntity(stack, user, entity, hand);
                }


            }
            return super.useOnEntity(stack, user, entity, hand);
        }
    }






    final float SWEEP_DAMAGE = 4;
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();
        if (!attacker.getWorld().isClient) {
            WeaponChargeComponent.IncrementSPADE(10);

        }
        return super.postHit(stack, target, attacker);
    }
    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return Math.round((float) WeaponChargeComponent.SPADE / WeaponChargeComponent.MAX_SPADE * 13); // full bar = max charge
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        // Glows between gold → magenta → red as it fills
        int red = (int) (220);
        int blue = (int) (30);
        int green = (int) (170);
        return (red << 16) | (green << 8) | blue; // RGB mix
    }
    public Text getName(ItemStack stack) {
        return Text.translatable(this.getTranslationKey(stack)).setStyle(Style.EMPTY.withColor(0xFFD700 ));
    }
    private void playInsertSound(World world, LivingEntity user) {
        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.ITEM_TOTEM_USE, SoundCategory.PLAYERS, 0.5f, 1.0f);
    }
}
