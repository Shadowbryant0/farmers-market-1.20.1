package net.shadow.farmersmarket.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.shadow.farmersmarket.FarmersMarket;
import net.shadow.farmersmarket.world.dimension.FMDimensions;

import java.util.Objects;

public class ShadowRealmVoidHandler {

        public static void init() {
            ServerTickEvents.END_SERVER_TICK.register(server -> {
                // Check all players every tick
                server.getPlayerManager().getPlayerList().forEach(ShadowRealmVoidHandler::checkPlayerVoidFall);
            });
        }

        private static void checkPlayerVoidFall(ServerPlayerEntity player) {
            // Don't check if player is already in void dimension


            // Check if player is below the void threshold for their dimension
            boolean inVoid = false;
            if (player.getWorld().getRegistryKey() == FMDimensions.SHADOW_REALM_KEY) {
                inVoid = player.getY() < -64;
            }



            if(player.isFallFlying())return;

            if (inVoid) {
                // Teleport player to void dimension
                ServerWorld end = Objects.requireNonNull(player.getServer()).getWorld(World.END);
                if (end != null) {
                    // Cancel fall damage by resetting fall distance before teleporting
                    player.fallDistance = 0.0f;

                    // Teleport to void dimension at Y=-63 (just above the barrier layer)
                    player.teleport(end, 0, 80, 0, 0.0f, 0.0f);

                    // Ensure fall distance remains 0 after teleportation
                    player.fallDistance = 0.0f;

                    // Send message to player
                    player.sendMessage(
                            net.minecraft.text.Text.translatable("fm.warped_end"),
                            false
                    );

                    FarmersMarket.LOGGER.info("Player {} fell into void from {} and was teleported to the end/tp",
                            player.getName().getString(), player.getWorld().getRegistryKey().getValue());
                }
            }
        }
    }
