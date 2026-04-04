package net.shadow.farmersmarket.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.client.font.UnihexFont;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.feature.EndPlacedFeatures;
import net.shadow.farmersmarket.FarmersMarket;
import net.shadow.farmersmarket.world.dimension.FMDimensions;
import net.shadow.farmersmarket.world.dimension.SpawnPlatforms;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.util.Objects;

@Mixin(EnderPearlEntity.class)
public abstract class EnderPearlMixin extends ThrownItemEntity {
    public EnderPearlMixin(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }
    @Unique boolean tp = true;


    @Inject(at = @At("TAIL"), method = "tick")
	private void init(CallbackInfo ci) {
        BlockPos pos = this.getBlockPos();
        World world = this.getWorld();
        Entity entity = this.getOwner();
        if (!(entity instanceof ServerPlayerEntity player)) return;
        if (player.getWorld().getRegistryKey() == World.END) {
            if (world.getBlockState(pos) == Blocks.VOID_AIR.getDefaultState()) {
                this.kill();
                if (entity == null) return;
                ServerWorld voidWorld = Objects.requireNonNull(player.getServer()).getWorld(FMDimensions.SHADOW_REALM_KEY);
                if (player.isSneaking()) {
                    if (voidWorld != null) {
                        player.fallDistance = 0.0f;

                        // Teleport to void dimension at Y=-63 (just above the barrier layer)
                        this.teleport(voidWorld, 0, 130, 0, PositionFlag.getFlags(1), 0.0f, 0.0f);
                        player.teleport(voidWorld, 0, 130, 0, 0.0f, 0.0f);

                        // Ensure fall distance remains 0 after teleportation
                        player.fallDistance = 0.0f;

                        // Send message to player
                        player.sendMessage(
                                net.minecraft.text.Text.translatable("fm.warped_shadow"),
                                false
                        );
                        BlockPos pos1 = new BlockPos(0, 125, 0);
                        SpawnPlatforms.ensureSpawnPlatform_shadow(voidWorld, pos1);
                        FarmersMarket.LOGGER.info("Player {} warped into void from {} and was teleported to void dimension",
                                player.getName().getString(), player.getWorld().getRegistryKey().getValue());
                    }
                }
            }
        }
		// This code is injected into the start of MinecraftServer.loadWorld()V
	}
}