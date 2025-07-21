package net.shadow.farmersmarket.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.shadow.farmersmarket.item.ModItems;


public class ShardEntity extends PersistentProjectileEntity {


        public ShardEntity(EntityType<? extends ShardEntity> entityType, World world) {
         super(entityType, world);
        }

        public ShardEntity(World world, double x, double y, double z) {
            super(EntityType.ARROW, x, y, z, world);

        }

        public ShardEntity(World world, LivingEntity owner) {
            super(EntityType.ARROW, owner, world);

        }





    @Override
        protected ItemStack asItemStack() {
            return null;
        }
        @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
            super.onEntityHit(entityHitResult);
            Entity entity = entityHitResult.getEntity();
            entity.damage(entity.getDamageSources().flyIntoWall(), 20);
        }

    public void initFromStack(ItemStack stack) {
        if (stack.isOf(ModItems.SHARDS)) {
System.out.println("detectedshard");
        }
    }

    public PersistentProjectileEntity createShard(World world, ItemStack stack, LivingEntity entity) {
     ShardEntity shardEntity = new ShardEntity(world, entity);
            shardEntity.initFromStack(stack);
            return shardEntity;
        }
}

