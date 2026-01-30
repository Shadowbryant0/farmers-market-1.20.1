package net.shadow.farmersmarket.util;

import com.ibm.icu.impl.ValidIdentifiers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SpearAction {

    final static double RANGE = 5;
    public static void Spearing(World world, PlayerEntity user, Hand hand, float velocity){
        Vec3d origin = user.getEyePos();
        Vec3d look   = user.getRotationVector().normalize();
        Vec3d end    = origin.add(look.multiply(RANGE));
        ItemStack stack = user.getStackInHand(hand);
        if (!(world instanceof ServerWorld serverWorld)) return;
        var hit = world.raycast(new RaycastContext(
                origin, end,
                RaycastContext.ShapeType.COLLIDER,
                RaycastContext.FluidHandling.NONE,
                user
        ));
        serverWorld.playSound(null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.PLAYERS, 3.0f, 1.0f);
        int particleCount = (int) (RANGE * 2.5);
        for (int i = 0; i < particleCount; i++) {
            double t = i / 2.5;
            Vec3d pos = origin.add(look.multiply(t));
            serverWorld.spawnParticles(ParticleTypes.SMOKE, pos.x, pos.y, pos.z, 1, 0, 0, 0, 0);
        }
        Vec3d impact = (hit.getType() == HitResult.Type.MISS) ? end : hit.getPos();
        LivingEntity directTarget = null;
        List<LivingEntity> potential = serverWorld.getEntitiesByClass(
                LivingEntity.class,
                new Box(origin, end).expand(1.0),
                e -> e != user && e.isAlive()
        );
        double minDist = Double.MAX_VALUE;
        for (LivingEntity e : potential) {
            double d = origin.distanceTo(e.getPos());
            if (d < minDist && d <= RANGE) {
                minDist = d;
                directTarget = e;
            }
        }
        if (directTarget != null) {
            directTarget.damage(serverWorld.getDamageSources().mobAttack(user), velocity);
            //impact = directTarget.getPos();
        }
    }
}
