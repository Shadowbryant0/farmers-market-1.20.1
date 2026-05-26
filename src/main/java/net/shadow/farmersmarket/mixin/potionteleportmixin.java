package net.shadow.farmersmarket.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.shadow.farmersmarket.components.entity.TeleportRandomlyComponent;
import net.shadow.farmersmarket.item.trinkets.endstuff.EnderManPendent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;

@Mixin(PotionItem.class)
public class potionteleportmixin {
	@Inject(at = @At("HEAD"), method = "finishUsing")
	private void init(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if(!world.isClient()) {
            if (user instanceof PlayerEntity player) {
                if (!(player.getAbilities().invulnerable)) {
                    if (EnderManPendent.isWearingTrinket(player)) {
                        if (PotionUtil.getPotion(stack) == Potions.WATER) {
                            this.teleportLogically(player);
                        }
                    }
                }
            }
        }
		// This code is injected into the start of MinecraftServer.loadWorld()V
	}
    @Unique
    protected void teleportLogically(PlayerEntity player){
        if (player.getWorld().isClient() || !player.isAlive()) {
            return;
        }
        BlockPos HostPos = player.getBlockPos();
        World world = player.getEntityWorld();
        ArrayList<BlockPos> possiblesafe = new ArrayList<>();
        ArrayList<BlockPos> rainsafe = new ArrayList<>();
        ArrayList<BlockPos> airborn = new ArrayList<>();
        ArrayList<BlockPos> airrain = new ArrayList<>();
        ArrayList<BlockPos> wall = new ArrayList<>();
        for (int dx = -5; dx <= 5; dx++) {
            for (int dy = -5; dy <= 5; dy++) {
                for (int dz = -5; dz <= 5; dz++) {
                    if (dx == 0 && dy == 0) continue;
                    BlockState possible1 = world.getBlockState(HostPos.add(dx, dy, dz)); // legs
                    BlockState possible2 = world.getBlockState(HostPos.add(dx, dy + 1, dz)); //head
                    BlockState possible0 = world.getBlockState(HostPos.add(dx, dy - 1, dz)); //under
                    BlockState possible3 = world.getBlockState(HostPos.add(dx, dy + 2, dz)); //block above
                    BlockState possible4 = world.getBlockState(HostPos.add(dx, dy + 3,dz)); //block above+1
                    BlockState possible5 = world.getBlockState(HostPos.add(dx, dy + 4,dz)); //block above+2
                    BlockState possible6 = world.getBlockState(HostPos.add(dx, dy + 5,dz)); //block above+3
                    BlockState possible7 = world.getBlockState(HostPos.add(dx, dy + 6,dz)); //block above+4
                    if (possible1.getBlock() == Blocks.AIR && possible2.getBlock() == Blocks.AIR && possible0.getBlock() == Blocks.AIR) {
                        if(world.isRaining()){
                            if(possible3.getBlock() != Blocks.AIR||possible4.getBlock() != Blocks.AIR||possible5.getBlock() != Blocks.AIR||possible6.getBlock() != Blocks.AIR||possible7.getBlock() != Blocks.AIR){
                                airrain.add(HostPos.add(dx, dy, dz));
                            }
                        }
                        if(HostPos.add(dx,dy-1,dz).getY() <= -63){
                            continue;
                        }
                        airborn.add(HostPos.add(dx, dy, dz));
                        continue;
                    }
                    if (possible1.getBlock() != Blocks.AIR && possible2.getBlock() != Blocks.AIR && possible0.getBlock() != Blocks.AIR && possible1.getBlock() != Blocks.WATER && possible2.getBlock() != Blocks.WATER && possible0.getBlock() != Blocks.WATER) {
                        if(HostPos.add(dx,dy-1,dz).getY() <= -62){
                            continue;
                        }
                        wall.add(HostPos.add(dx, dy, dz));
                        continue;
                    }
                    if (possible1.getBlock() == Blocks.AIR && possible2.getBlock() == Blocks.AIR && possible0.getBlock() != Blocks.AIR && possible0.getBlock() != Blocks.WATER) {
                        if(world.isRaining()){
                            if(possible3.getBlock() != Blocks.AIR||possible4.getBlock() != Blocks.AIR||possible5.getBlock() != Blocks.AIR||possible6.getBlock() != Blocks.AIR||possible7.getBlock() != Blocks.AIR){
                                rainsafe.add(HostPos.add(dx, dy, dz));
                            }
                        }
                        possiblesafe.add(HostPos.add(dx, dy, dz));
                    }

                }
            }
        }

        if(!possiblesafe.isEmpty()) {
            if(!rainsafe.isEmpty()) {
                int selected =player.getRandom().nextBetween(0,rainsafe.size()-1);
                BlockPos tp = rainsafe.get(selected);
                player.teleport(tp.getX()+.5, tp.getY(), tp.getZ()+.5);
                return;
            }
            int selected =player.getRandom().nextBetween(0,possiblesafe.size()-1);
            BlockPos tp = possiblesafe.get(selected);
            player.teleport(tp.getX()+.5, tp.getY(), tp.getZ()+.5);
            return;
        }

        if(!airborn.isEmpty()) {
            if(!airrain.isEmpty()) {
                int selected =player.getRandom().nextBetween(0,airrain.size()-1);
                BlockPos tp = airrain.get(selected);
                player.teleport(tp.getX()+.5, tp.getY(), tp.getZ()+.5);
                return;
            }
            int selected =player.getRandom().nextBetween(0,airborn.size()-1);
            BlockPos tp = airborn.get(selected);
            player.teleport(tp.getX()+.5, tp.getY(), tp.getZ()+.5);
            return;
        }
        if(!wall.isEmpty()) {
            int selected =player.getRandom().nextBetween(0,wall.size()-1);
            BlockPos tp = wall.get(selected);
            player.teleport(tp.getX()+.5, tp.getY(), tp.getZ()+.5);
            return;
        }

        player.teleport(HostPos.getX(), HostPos.getY()+5, HostPos.getZ());
    }
}