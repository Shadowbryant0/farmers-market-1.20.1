package net.shadow.farmersmarket.item.custom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;

import net.minecraft.entity.projectile.PersistentProjectileEntity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.shadow.farmersmarket.entity.custom.ShardEntity;

public class CrystalineShardItem extends Item {
        public CrystalineShardItem(Item.Settings settings) {
            super(settings);
        }


    public PersistentProjectileEntity createShard(World world, ItemStack stack, LivingEntity shooter) {
        ShardEntity shardEntity = new ShardEntity(world, shooter);
        shardEntity.initFromStack(stack);
        return shardEntity;

    }


}
