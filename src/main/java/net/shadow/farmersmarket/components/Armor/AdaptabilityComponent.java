package net.shadow.farmersmarket.components.Armor;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.DamageTypeTags;
import net.shadow.farmersmarket.components.RainDamageComponent;
import net.shadow.farmersmarket.item.ModItems;
import net.shadow.farmersmarket.util.FMEnchantCheck;
import net.shadow.farmersmarket.util.FarmersMarketDamageTagsCustom;

public class AdaptabilityComponent implements Component, CommonTickingComponent, AutoSyncedComponent {
    public final PlayerEntity player;
public ItemStack stack;
    public AdaptabilityComponent(PlayerEntity player) {
        this.player = player;
    }
    public static double PROJECTILE = 0;
    public static double FIRE = 0;
    public static double EXPLOSIVE = 0;
    public static double KINETIC = 0;

    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        PROJECTILE = nbtCompound.getDouble("projectile");
        FIRE = nbtCompound.getDouble("fire");
        EXPLOSIVE = nbtCompound.getDouble("explosive");
        KINETIC = nbtCompound.getDouble("kinetic");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putDouble("projectile", PROJECTILE);
        nbtCompound.putDouble("fire", FIRE);
        nbtCompound.putDouble("explosive", EXPLOSIVE);
        nbtCompound.putDouble("kinetic", KINETIC);
    }

    @Override
    public void tick() {
        if(FMEnchantCheck.getAdapting(player)>1){
        }
    }

    public static void Decrease(DamageSource source){
        if(source.isIn(DamageTypeTags.IS_FIRE)){
            PROJECTILE = Math.max(PROJECTILE - 1, 0);
            EXPLOSIVE = Math.max(EXPLOSIVE - 1, 0);
            KINETIC = Math.max(KINETIC - 1, 0);
        }
        if(source.isIn(DamageTypeTags.IS_PROJECTILE)){
            FIRE = Math.max(FIRE - 1, 0);
            EXPLOSIVE = Math.max(EXPLOSIVE - 1, 0);
            KINETIC = Math.max(KINETIC - 1, 0);
        }
        if(source.isIn(FarmersMarketDamageTagsCustom.KINETIC)){
            FIRE = Math.max(FIRE - 1, 0);
            EXPLOSIVE = Math.max(EXPLOSIVE - 1, 0);
            PROJECTILE = Math.max(PROJECTILE - 1, 0);
        }
        if(source.isIn(DamageTypeTags.IS_EXPLOSION)){
            PROJECTILE = Math.max(PROJECTILE - 1, 0);
            FIRE = Math.max(FIRE - 1, 0);
            KINETIC = Math.max(KINETIC - 1, 0);
        }
    }

    public static void IncrementAdaptability(DamageSource source){
        if(source.isIn(DamageTypeTags.IS_FIRE)){
            FIRE = Math.min(FIRE + 1, 2);
        }
        if(source.isIn(DamageTypeTags.IS_PROJECTILE)){
            PROJECTILE = Math.min(PROJECTILE + 1, 2);
        }
        if(source.isIn(FarmersMarketDamageTagsCustom.KINETIC)){
            KINETIC = Math.min(KINETIC + 1, 2);
        }
        if(source.isIn(DamageTypeTags.IS_EXPLOSION)){
            EXPLOSIVE = Math.min(EXPLOSIVE + 1, 2);
        }
    }



}
