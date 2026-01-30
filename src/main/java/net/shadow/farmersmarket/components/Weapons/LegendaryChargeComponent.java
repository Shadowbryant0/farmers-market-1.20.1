package net.shadow.farmersmarket.components.Weapons;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.shadow.farmersmarket.item.ModItems;
import net.shadow.farmersmarket.item.custom.weapons.legendaries.SpearWeapon;
import net.shadow.farmersmarket.util.FMEnchantCheck;
import net.shadow.farmersmarket.util.SpearAction;

public class LegendaryChargeComponent implements Component, CommonTickingComponent, AutoSyncedComponent {
    public final PlayerEntity player;
public ItemStack stack;
    public LegendaryChargeComponent(PlayerEntity player) {
        this.player = player;
    }

    public static int SPEAR = 0;
    public static int SPEAR_A = 0;
    public static int MAX_SPEAR = 60;
    public static int MAX_SPEAR_A = 100;
    public static Vec3d VELOCITY_RAW= null;
    public static float VELOCITY= 0;
    public static float VELOCITY_NON_R= 0;


    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        SPEAR = nbtCompound.getInt("spear");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putInt("spear", SPEAR);

    }

    @Override
    public void tick() {
        if(SPEAR_A<=0) {
            if (SPEAR < MAX_SPEAR) {
                SPEAR++;
            }
        }
        VELOCITY_RAW = SpearWeapon.getAmplifiedMovement(player);
        VELOCITY = (Math.round(2*(Math.abs(VELOCITY_RAW.getX()) + Math.abs(VELOCITY_RAW.getY())+Math.abs(VELOCITY_RAW.getZ()))));
        if(SPEAR_A>0) {
            SPEAR_A--;

            if (player.isHolding(ModItems.SPEAR)) {

                SpearAction.Spearing(player.getWorld(), player, player.getActiveHand(), VELOCITY);
            }
        }
    }
    public static void UseSPEAR(){
        SPEAR_A = MAX_SPEAR_A;
        SPEAR = 0;
    }

    public static void ChargeAll() {
        SPEAR = MAX_SPEAR;
    }
}
