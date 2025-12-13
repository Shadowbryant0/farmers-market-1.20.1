package net.shadow.farmersmarket.entity.projectile;


import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.shadow.farmersmarket.entity.FarmersMarketEntities;
import org.jetbrains.annotations.Nullable;

public class DragonTear extends ArrowEntity {

    public DragonTear (EntityType<? extends DragonTear> entityType, World world) {
        super(entityType, world);
    }

    // :0 woahs
    public DragonTear (World world, LivingEntity owner) {
        super(FarmersMarketEntities.TEAR, world);
        this.setOwner(owner);
        this.setPosition(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
    }



    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient) {
            this.getWorld().addParticle(ParticleTypes.BUBBLE, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        }

    }
    @Override
    protected void onEntityHit(EntityHitResult hitResult) {
        super.onEntityHit(hitResult);
    }
    @Override
    protected void onBlockHit(BlockHitResult hitResult) {
        super.onBlockHit(hitResult);
        this.discard();
    }
    protected float getDragInRainOrWater(){
        return 1F;
    }
    protected Boolean SeaProjectile(){
        return true;
    }
    @Override
    protected float getDragInWater() {
        return 1F;
    }
    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
    }

}