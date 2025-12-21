package net.shadow.farmersmarket.item.custom.weapons;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.shadow.farmersmarket.components.Weapons.ParryComponent;
import net.shadow.farmersmarket.item.materials.WeaponMaterials;

public class PirateSaberItem extends SwordItem {

    private static final int COOLDOWN_TICKS = 40;
    public PirateSaberItem(Settings settings) {

        super(WeaponMaterials.SABER, 2, -2.3F, settings);
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ParryComponent.ParryAction();

        user.getItemCooldownManager().set(this, (COOLDOWN_TICKS));
        return super.use(world, user, hand);
    }
    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }
    @Override
    public int getItemBarStep(ItemStack stack) {
        return Math.round((float) ParryComponent.PARRY / ParryComponent.PARRY_MAX * 13); // full bar = max charge
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return (200 << 16) | (50 << 8) | 200; // RGB mix
    }
}
