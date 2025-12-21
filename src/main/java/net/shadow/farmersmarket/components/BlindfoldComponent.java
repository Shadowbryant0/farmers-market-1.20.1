package net.shadow.farmersmarket.components;

import dev.emi.trinkets.TrinketSlot;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.SlotType;
import dev.emi.trinkets.api.TrinketComponent;
import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.shadow.farmersmarket.effects.ModEffects;
import net.shadow.farmersmarket.enchantments.FarmersMarketEnchants;
import net.shadow.farmersmarket.item.trinkets.BlindfoldOfSightItem;

import java.util.List;

public class BlindfoldComponent implements Component, CommonTickingComponent, AutoSyncedComponent {
    public final PlayerEntity player;
public ItemStack stack;
    public BlindfoldComponent(PlayerEntity player) {
        this.player = player;
    }
    public static int BLIND = 0;
    public static boolean BOOLIAN = false;

    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        BLIND = nbtCompound.getInt("blind");
        BOOLIAN = nbtCompound.getBoolean("boolian");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putInt("blind", BLIND);
        nbtCompound.putBoolean("boolian", BOOLIAN);
    }

    @Override
    public void tick() {

        if (BlindfoldOfSightItem.isWearingTrinket(player)) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 100, 3, false, false, false));

        }
    }



}
