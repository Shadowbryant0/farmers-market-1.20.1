package net.shadow.farmersmarket.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;

public class RapierWeaponItem extends Item {
    public RapierWeaponItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 20;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        onUse;
        if (entity == null) return;

        double length = 0;

        Vec3d look = entity.getRotationVec(1.0F);
        entity.setVelocity(look.x * 2.5, look.y * 2.0, look.z * 2.5);
        entity.velocityModified = true;

        if (entity instanceof PlayerEntity player) {
            if (player.isFallFlying()) {
                player.getItemCooldownManager().set(ItemStack.getItem(), 1000);
            } else {
                player.getItemCooldownManager().set(ItemStack.getItem(), 200);
            }
        }

        for (int i = 0; i < 10; i++) {
            Vec3d center = new Vec3d(
                    x + look.x * length,
                    y + 1.6 + look.y * length,
                    z + look.z * length
            );

            List<Entity> foundEntities = world.getOtherEntities(
                            entity,
                            new Box(center.subtract(1, 1, 1), center.add(1, 1, 1)),
                            e -> e instanceof LivingEntity
                    ).stream().sorted(Comparator.comparingDouble(e -> e.squaredDistanceTo(center)))
                    .collect(Collectors.toList());

            for (entity target : foundEntities) {
                if (!target.equals(entity) && target instanceof LivingEntity livingTarget && !world.isClient) {
                    livingTarget.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 80, 0, false, true));
                    livingTarget.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 80, 0, false, true));
                }
            }

            length += 1;
        }
    }`

    }
    onUse
