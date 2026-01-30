package net.shadow.farmersmarket.mixin.aquiifermixins;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public abstract class TouchingRainMixinDrag extends ProjectileEntity {

    @Unique
    protected float getDragInRainOrWater() {
        return 0.6F;
    }
    @Unique
    protected Boolean SeaProjectile(){
        return false;
    }
    @Unique
    protected abstract void applyDrag(float drag);

    @Unique
    protected abstract void spawnBubbleParticles(Vec3d pos);

    public TouchingRainMixinDrag(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("TAIL"), method = "Lnet/minecraft/entity/projectile/PersistentProjectileEntity;tick()V")
    private void RainDrag(CallbackInfo ci) {
        Vec3d vec3d3 = this.getPos();
        if(SeaProjectile()){
        if(this.isTouchingWaterOrRain()) {
            this.applyDrag(getDragInRainOrWater());
            this.spawnBubbleParticles(vec3d3);
        }else{
            this.applyDrag(0.6F);
        }
        }
    }


}

