package net.shadow.farmersmarket.item.custom.weapons;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.shadow.farmersmarket.item.materials.ExcalatrowlMats;

public class BoopStick extends SwordItem {
    public BoopStick(Settings settings) {
        super(ExcalatrowlMats.INSTANCE, 3, -2.5F, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.decrement(1);
        target.kill();
        return super.postHit(stack, target, attacker);
    }
}
