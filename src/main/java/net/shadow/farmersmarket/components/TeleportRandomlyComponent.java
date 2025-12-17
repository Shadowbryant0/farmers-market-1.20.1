package net.shadow.farmersmarket.components;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.shadow.farmersmarket.item.trinkets.endstuff.EnderManPendent;

public class TeleportRandomlyComponent implements Component, CommonTickingComponent, AutoSyncedComponent {
    public final PlayerEntity player;
public ItemStack stack;
    public TeleportRandomlyComponent(PlayerEntity player) {
        this.player = player;
    }
public static boolean enderman = false;

    public static int PARRY_MAX = 20;
    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        enderman = nbtCompound.getBoolean("enderman");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {

        nbtCompound.putBoolean("enderman", enderman);
    }

    @Override
    public void tick() {
        if(EnderManPendent.isWearingTrinket(player)) {
            if(!(player.getAbilities().invulnerable)) {
                if (player.isWet()) {
                    this.teleportRandomly();
                    RainDamageComponent.RainCounter(1);
                }
            }
        }
    }

    protected boolean teleportRandomly() {
        if (player.getWorld().isClient() || !player.isAlive()) {
            return false;
        }
        double d = player.getX() + (player.getRandom().nextDouble() - 0.5) * 8.0;
        double e = player.getY();
        double f = player.getZ() + (player.getRandom().nextDouble() - 0.5) * 8.0;
        player.teleport(d, e, f);
        return true;
    }

}
