package net.shadow.farmersmarket.item.custom.weapons;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.shadow.farmersmarket.item.components.Weapons.BowChargeComponent;
import net.shadow.farmersmarket.item.materials.Greatmat;

public class TestingBow extends SwordItem {
    public int charged;
    public int charged_max = 20;
    public TestingBow(Settings settings) {
        super(Greatmat.INSTANCE, 3, -2.6F, settings);
    }

    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putInt("charge", BowChargeComponent.ARROW);
    }
    public void readFromNbt(NbtCompound nbtCompound) {
        charged = nbtCompound.getInt("arrows");
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
BowChargeComponent.minusArrow();
        return super.use(world, user, hand);
    }
    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }
    @Override
    public int getItemBarStep(ItemStack stack) {
        int charge = getCharge(stack);
        return Math.round((float) BowChargeComponent.ARROW / BowChargeComponent.ARROW_MAX * 13); // full bar = max charge
    }
    @Override
    public int getItemBarColor(ItemStack stack) {
        return (200 << 16) | (50 << 8) | 200; // RGB mix
    }
    private int getCharge(ItemStack stack) {
        return stack.getOrCreateNbt().getInt("charge");
    }
}
