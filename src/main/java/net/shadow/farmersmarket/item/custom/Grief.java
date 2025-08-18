package net.shadow.farmersmarket.item.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Grief extends Item {
    private static final int FULL_CHARGE_TICKS = 60; // 2 seconds
    private static final int COOLDOWN_TICKS = 1200;

    public Grief(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand); // Begin charging


        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000; // Like bow: hold indefinitely
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        int usedTicks = this.getMaxUseTime(stack) - remainingUseTicks;

        if (!world.isClient && user instanceof ServerPlayerEntity serverPlayer) {



                if (usedTicks >= FULL_CHARGE_TICKS && serverPlayer.experienceLevel >= 0) {
                    if(serverPlayer.getAttacker() instanceof PlayerEntity){
                        {
                            serverPlayer.damage(serverPlayer.getDamageSources().wither(),10);
                        }
                    }else if(serverPlayer.experienceLevel >= 5) {
                        serverPlayer.addExperienceLevels(-5);
                    } else if(serverPlayer.experienceLevel >= 4) {
                        serverPlayer.addExperienceLevels(-4);
                        serverPlayer.damage(serverPlayer.getDamageSources().wither(),2);

                    } else if(serverPlayer.experienceLevel >= 3) {
                        serverPlayer.addExperienceLevels(-3);
                        serverPlayer.damage(serverPlayer.getDamageSources().wither(),4);

                    } else if(serverPlayer.experienceLevel >= 2) {
                        serverPlayer.addExperienceLevels(-2);
                        serverPlayer.damage(serverPlayer.getDamageSources().wither(),6);

                    } else if(serverPlayer.experienceLevel >= 1) {
                        serverPlayer.addExperienceLevels(-1);
                        serverPlayer.damage(serverPlayer.getDamageSources().wither(),7);

                    } else
                    {
                        serverPlayer.damage(serverPlayer.getDamageSources().wither(),8);
                    }

                    user.getWorld().spawnEntity(new EvokerFangsEntity(user.getWorld(), user.getBlockPos().toCenterPos().getX(), user.getBlockPos().getY(), user.getBlockPos().toCenterPos().getZ(), 0, 0, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(user.getWorld(), user.getBlockPos().toCenterPos().getX()-1, user.getBlockPos().getY(), user.getBlockPos().toCenterPos().getZ()+1, 0, 5, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(user.getWorld(), user.getBlockPos().toCenterPos().getX()-1, user.getBlockPos().getY(), user.getBlockPos().toCenterPos().getZ(), 0, 5, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(user.getWorld(), user.getBlockPos().toCenterPos().getX()-1, user.getBlockPos().getY(), user.getBlockPos().toCenterPos().getZ()-1, 0, 5, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(user.getWorld(), user.getBlockPos().toCenterPos().getX()+1, user.getBlockPos().getY(), user.getBlockPos().toCenterPos().getZ()+1, 0, 5, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(user.getWorld(), user.getBlockPos().toCenterPos().getX()+1, user.getBlockPos().getY(), user.getBlockPos().toCenterPos().getZ(), 0, 5, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(user.getWorld(), user.getBlockPos().toCenterPos().getX()+1, user.getBlockPos().getY(), user.getBlockPos().toCenterPos().getZ()-1, 0, 5, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(user.getWorld(), user.getBlockPos().toCenterPos().getX(), user.getBlockPos().getY(), user.getBlockPos().toCenterPos().getZ()+1, 0, 5, user));
                    user.getWorld().spawnEntity(new EvokerFangsEntity(user.getWorld(), user.getBlockPos().toCenterPos().getX(), user.getBlockPos().getY(), user.getBlockPos().toCenterPos().getZ()-1, 0, 5, user));
                    BlockPos spawnPos = serverPlayer.getSpawnPointPosition();
                RegistryKey<World> spawnDim = serverPlayer.getSpawnPointDimension();

                ServerWorld destinationWorld;
                Vec3d teleportPos;

                    serverPlayer.getItemCooldownManager().set(this, COOLDOWN_TICKS);

                if (spawnPos != null && spawnDim != null) {
                    destinationWorld = serverPlayer.getServer().getWorld(spawnDim);
                    teleportPos = Vec3d.ofCenter(spawnPos);
                } else {
                    destinationWorld = serverPlayer.getServer().getOverworld();
                    teleportPos = Vec3d.ofCenter(destinationWorld.getSpawnPos());
                }

                    if (destinationWorld != null) {
                    // Consume XP and teleport

                    serverPlayer.teleport(destinationWorld,
                            teleportPos.x,
                            teleportPos.y,
                            teleportPos.z,
                            serverPlayer.getYaw(),
                            serverPlayer.getPitch());
                    }


                }
            }
        }

}
